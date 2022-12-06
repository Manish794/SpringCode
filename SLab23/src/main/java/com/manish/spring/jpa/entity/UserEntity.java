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
@Table(name="myusers")
public class UserEntity implements Serializable {

    @Id
    @GenericGenerator(
            name = "userIdGenerator",
            strategy = "com.manish.spring.jpa.generator.UserIdGenerator"
    )
    @GeneratedValue( generator = "userIdGenerator")
    private String userId;

    private String fullName;
    private String email;
    private long phone;
    private String loginId;
    private String loginPassword;
    private String role;

}
