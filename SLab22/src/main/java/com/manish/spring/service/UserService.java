package com.manish.spring.service;

import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.repo.UserRepo;
import com.manish.spring.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserResponse<String> addUser(User user){
        UserResponse<String> response = new UserResponse<>();
        try {
            Optional<UserEntity> optionalEntity = userRepo.findByEmail(user.getEmail());
            if (!optionalEntity.isPresent()) {
                optionalEntity = userRepo.findByPhone(user.getPhone());
                if (!optionalEntity.isPresent()) {
                    optionalEntity = userRepo.findByLoginId(user.getLoginId());
                    if (!optionalEntity.isPresent()) {
                        UserEntity entity = userRepo.save(UserTransformer.getUserEntity(user));
                        response.setData(entity.getUserId());
                    } else {
                        response.setErrorCode("LOGIN_ID_ALREADY_USED");
                        response.setErrorMessage("LoginId " + user.getLoginId() + " is not available");
                    }
                } else {
                    response.setErrorCode("PHONE_ALREADY_USED");
                    response.setErrorMessage("Phone " + user.getPhone() + " is not available");
                }
            } else {
                response.setErrorCode("EMAIL_ALREADY_USED");
                response.setErrorMessage("Email " + user.getEmail() + " is not available");
            }
        }catch(Exception e){
            response.setErrorCode("ERROR_ADDING_USER");
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    public UserResponse<User> updateUser(User user){
        UserResponse<User> response = new UserResponse<>();
        Optional<UserEntity> optionalUserEntity = userRepo.findById(user.getUserId());
        if(!optionalUserEntity.isPresent()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+user.getUserId()+" is not available");
        } else {
            List<UserEntity> userEntities = userRepo.findAllByEmailOrPhoneOrLoginIdAndUserIdNot(user.getEmail(), user.getPhone(), user.getLoginId(), user.getUserId());
            if(null == userEntities || userEntities.size() < 2) {
                if(userEntities.size() == 0 || (userEntities.size() == 1 && userEntities.get(0).getUserId().equals(user.getUserId()))){
                    UserEntity userEntity = optionalUserEntity.get();
                    UserTransformer.updateUserEntity(userEntity, user);
                    userRepo.saveAndFlush(userEntity);
                    response.setData(UserTransformer.getUser(userEntity));
                } else {
                    response.setErrorCode("EMAIL_OR_PHONE_OR_LOGIN_ID_ALREADY_USED");
                    response.setErrorMessage("Email "+user.getEmail()+", Phone "+ user.getPhone()+", LoginID "+ user.getLoginId());
                }

            } else{
                response.setErrorCode("EMAIL_OR_PHONE_OR_LOGIN_ID_ALREADY_USED");
                response.setErrorMessage("Email "+user.getEmail()+", Phone "+ user.getPhone()+", LoginID "+ user.getLoginId());
            }

        }
        return response;
    }

    public UserResponse<User> getUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        Optional<UserEntity> optionalUserEntity = userRepo.findById(userId);
        if(!optionalUserEntity.isPresent()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        } else {
            response.setData(UserTransformer.getUser(optionalUserEntity.get()));
        }
        return response;
    }

    public UserResponse<User> getUserByPhone(long phone){
        UserResponse<User> response = new UserResponse<>();
        Optional<UserEntity> optionalUserEntity = userRepo.findByPhone(phone);
        if(!optionalUserEntity.isPresent()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with phone "+phone+" is not available");
        } else {
            response.setData(UserTransformer.getUser(optionalUserEntity.get()));
        }
        return response;
    }

    public UserResponse<User> getUserByEmail(String email){
        UserResponse<User> response = new UserResponse<>();
        Optional<UserEntity> optionalUserEntity = userRepo.findByEmail(email);
        if(!optionalUserEntity.isPresent()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with email "+email+" is not available");
        } else {
            response.setData(UserTransformer.getUser(optionalUserEntity.get()));
        }
        return response;
    }

    public UserResponse<List<User>> getAllUsers(){
        UserResponse<List<User>> response = new UserResponse<>();
        List<UserEntity> userEntities = userRepo.findAll();
        if(userEntities == null || userEntities.isEmpty()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("No user available");
        } else {
            response.setData(UserTransformer.getUsers(userEntities));
        }
        return response;
    }

    public UserResponse<User> deleteUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        try {
            Optional<UserEntity> optionalUserEntity = userRepo.findById(userId);
            if(!optionalUserEntity.isPresent()) {
                response.setErrorCode("USER_NOT_FOUND");
                response.setErrorMessage("User with id "+userId+" is not available");
            } else {
                userRepo.deleteById(userId);
                response.setData(UserTransformer.getUser(optionalUserEntity.get()));
            }
        } catch(Exception e){
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        }
        return response;
    }

    public UserResponse<User> loginUser(Login login){
        UserResponse<User> response = new UserResponse<>();
        byte[] result = Base64.getEncoder().encode(login.getLoginPassword().getBytes());
        Optional<UserEntity> optionalUserEntity = userRepo.findByLoginIdAndLoginPassword(login.getLoginId(), new String(result));
        if(!optionalUserEntity.isPresent()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("Invalid username or password");
        } else {
            response.setData(UserTransformer.getUser(optionalUserEntity.get()));
        }
        return response;
    }

    public UserResponse<Boolean> deleteAll(){
        UserResponse<Boolean> response = new UserResponse<>();
       try {
           userRepo.deleteAll();
           response.setData(true);
       } catch(Exception e) {
           response.setErrorCode("USER_NOT_FOUND");
           response.setErrorMessage("No Record to Delete");
       }
        return response;
    }

}
