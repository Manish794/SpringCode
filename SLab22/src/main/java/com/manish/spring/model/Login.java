package com.manish.spring.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Login {
    private String loginId;
    private String loginPassword;
}
