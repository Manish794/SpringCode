package com.manish.spring.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private String userId;
    private String fullName;
    private String email;
    private long phone;
    private String loginId;
    private String loginPassword;

}
