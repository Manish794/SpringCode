package com.manish.spring.jpa.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@ToString

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="mybooks")
public class BookEntity implements Serializable {

    @Id
    @GenericGenerator(
            name = "bookIdGenerator",
            strategy = "com.manish.spring.jpa.generator.BookIdGenerator"
    )
    @GeneratedValue( generator = "bookIdGenerator")
    private String bookId;
    private String bookName;


}
