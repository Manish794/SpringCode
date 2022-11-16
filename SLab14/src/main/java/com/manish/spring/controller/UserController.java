package com.manish.spring.controller;

import com.manish.spring.domain.Login;
import com.manish.spring.domain.User;
import com.manish.spring.domain.UserResponse;
import com.manish.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public UserResponse<String> addUser(User user){
        return userService.addUser(user);
    }

    public UserResponse<User> updateUser(User user){
        return userService.updateUser(user);
    }

    public UserResponse<User> getUser(String userId){
        return userService.getUser(userId);
    }

    public UserResponse<User> getUserByPhone(long phone){
        return userService.getUserByPhone(phone);
    }

    public UserResponse<User> getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

    public UserResponse<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    public UserResponse<User> deleteUser(String userId){
        return userService.deleteUser(userId);
    }

    public UserResponse<User> loginUser(Login login){
        return userService.loginUser(login);
    }

}
