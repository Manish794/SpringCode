package com.manish.spring.model;

import lombok.*;

import java.io.Serializable;

@Data
@ToString

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private String bookId;
    private String bookName;

}
