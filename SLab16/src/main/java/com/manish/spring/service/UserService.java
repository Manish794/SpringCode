package com.manish.spring.service;

import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserResponse<String> addUser(User user){
        UserResponse<String> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(user.getEmail());
        if(result == null) {
            result =  userRepo.getUserByPhone(user.getPhone());
            if(result == null) {
                result =  userRepo.getUserByLoginId(user.getLoginId());
                if(result == null) {
                    String userId = userRepo.addUser(user);
                    response.setData(userId);
                } else{
                    response.setErrorCode("LOGIN_ID_ALREADY_USED");
                    response.setErrorMessage("LoginId "+user.getLoginId()+" is not available");
                }
            } else {
                response.setErrorCode("PHONE_ALREADY_USED");
                response.setErrorMessage("Phone "+user.getPhone()+" is not available");
            }
        } else {
            response.setErrorCode("EMAIL_ALREADY_USED");
            response.setErrorMessage("Email "+user.getEmail()+" is not available");
        }
        return response;
    }

    public UserResponse<User> updateUser(User user){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(user.getEmail());
        if(result == null) {
            result =  userRepo.getUserByPhone(user.getPhone());
            if(result == null) {
                result =  userRepo.getUserByLoginId(user.getLoginId());
                if(result == null) {
                    result = userRepo.updateUser(user);
                    if(result == null) {
                        response.setErrorCode("USER_NOT_FOUND");
                        response.setErrorMessage("User with id "+user.getUserId()+" is not available");
                    } else {
                        response.setData(result);
                    }
                } else{
                    response.setErrorCode("LOGIN_ID_ALREADY_USED");
                    response.setErrorMessage("LoginId "+user.getLoginId()+" is not available");
                }
            } else {
                response.setErrorCode("PHONE_ALREADY_USED");
                response.setErrorMessage("Phone "+user.getPhone()+" is not available");
            }
        } else {
            response.setErrorCode("EMAIL_ALREADY_USED");
            response.setErrorMessage("Email "+user.getEmail()+" is not available");
        }
        return response;
    }

    public UserResponse<User> getUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUser(userId);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> getUserByPhone(long phone){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByPhone(phone);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with phone "+phone+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> getUserByEmail(String email){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(email);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with email "+email+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<List<User>> getAllUsers(){
        UserResponse<List<User>> response = new UserResponse<>();
        List<User> result = userRepo.getAllUsers();
        if(result == null || result.isEmpty()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("No user available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> deleteUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.deleteUser(userId);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> loginUser(Login login){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.loginUser(login);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("Invalid username or password");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<Boolean> deleteAll(){
        UserResponse<Boolean> response = new UserResponse<>();
        boolean result = userRepo.deleteAll();
        if(!result) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("No Record to Delete");
        } else {
            response.setData(result);
        }
        return response;
    }

}
