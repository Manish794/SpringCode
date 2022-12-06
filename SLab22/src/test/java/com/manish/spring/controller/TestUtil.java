package com.manish.spring.controller;

import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;

import java.util.Optional;

public class TestUtil {
    public static User getUser(){
        return User.builder()
                .email("testemail")
                .fullName("testname")
                .phone(121212)
                .loginId("testlogin")
                .loginPassword("testpwd")
                .build();
    }

    public static UserResponse<String> getAddUserSuccessData(){
        UserResponse<String> response = new UserResponse<>();
        response.setData("U-001");
        return response;
    }

    public static UserResponse<String> getAddUserErrorData(){
        UserResponse<String> response = new UserResponse<>();
        response.setErrorCode("ERROR_ADD_USER");
        response.setErrorMessage("Unable to add user");
        return response;
    }


    /**
     * Below are the method to create test data for UserService
     */
    public static Optional<UserEntity> getOptionalUserEntity(){
        return Optional.of(UserEntity.builder()
                        .userId("U-001")
                .build());
    }

    public static UserEntity getUserEntity(){
        return UserEntity.builder()
                .userId("U-001")
                .build();
    }

}
