package com.manish.spring.model;

import lombok.*;

import java.io.Serializable;

@Data
@ToString

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String userId;
    private String fullName;
    private String email;
    private long phone;
    private String loginId;
    private String loginPassword;

}
