package com.manish.spring.controller;

import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        System.out.println("UserController()");
        this.userService = userService;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<String> addUser(@RequestBody User user){
        System.out.println("UserController -> addUser "+user);
        return userService.addUser(user);
    }

    @PostMapping(value="/login", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> loginUser(@RequestBody Login login){
        System.out.println("UserController -> loginUser ");
        return userService.loginUser(login);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> updateUser(@RequestBody User user){
        System.out.println("UserController -> updateUser"+user);
        System.out.println("Hello Guys");
        return userService.updateUser(user);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<List<User>> getAllUsers(){
        System.out.println("UserController -> getAllUsers");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{myUserId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> getUser(@PathVariable("myUserId") String userId){
        System.out.println("UserController -> getUser");
        return userService.getUser(userId);
    }

    @GetMapping(value = "/searchByPhone", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> getUserByPhone(@RequestParam("phone") long phone){
        System.out.println("UserController -> getUserByPhone");
        return userService.getUserByPhone(phone);
    }

    @GetMapping(value = "/searchByEmail", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> getUserByEmail(@RequestParam("email") String email){
        System.out.println("UserController -> getUserByEmail");
        return userService.getUserByEmail(email);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<Boolean> deleteAll(){
        System.out.println("UserController -> deleteAll");
        return userService.deleteAll();
    }

    @DeleteMapping(value = "/{myUserId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<User> deleteUser(@PathVariable("myUserId")String userId){
        System.out.println("UserController -> deleteUser");
        return userService.deleteUser(userId);
    }



}
