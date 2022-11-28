package com.manish.spring.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponse<T> {
    private T data;
    private String errorCode;
    private String errorMessage;
}
