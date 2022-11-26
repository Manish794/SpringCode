package com.manish.spring.transformer;

import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.User;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserTransformer {

    public static UserEntity getUserEntity(User user){
        if(!ObjectUtils.isEmpty(user)){
            byte[] result = Base64.getEncoder().encode(user.getLoginPassword().getBytes());
            UserEntity userEntity = UserEntity
                    .builder()
                    .userId(user.getUserId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .loginId(user.getLoginId())
                    .loginPassword(new String(result))
                    .build();
            return userEntity;
        }
        return null;
    }

    public static User getUser(UserEntity userEntity){
        if(!ObjectUtils.isEmpty(userEntity)){
            User user = User
                    .builder()
                    .userId(userEntity.getUserId())
                    .fullName(userEntity.getFullName())
                    .email(userEntity.getEmail())
                    .phone(userEntity.getPhone())
                    .loginId(userEntity.getLoginId())
                    .loginPassword(userEntity.getLoginPassword())
                    .build();
            return user;
        }
        return null;
    }

    public static List<User> getUsers(List<UserEntity> userEntities){
        if(!CollectionUtils.isEmpty(userEntities)) {
            List<User> users = new ArrayList<>(userEntities.size());
            for(UserEntity userEntity : userEntities){
                User user = User
                        .builder()
                        .userId(userEntity.getUserId())
                        .fullName(userEntity.getFullName())
                        .email(userEntity.getEmail())
                        .phone(userEntity.getPhone())
                        .loginId(userEntity.getLoginId())
                        .loginPassword(userEntity.getLoginPassword())
                        .build();
                users.add(user);
            }
            return users;
        }
        return null;
    }

    public static void updateUserEntity(UserEntity userEntity, User user){
        if(!ObjectUtils.isEmpty(userEntity) && !ObjectUtils.isEmpty(user)){
            byte[] result = Base64.getEncoder().encode(user.getLoginPassword().getBytes());
            userEntity.setUserId(user.getUserId());
            userEntity.setFullName(user.getFullName());
            userEntity.setEmail(user.getEmail());
            userEntity.setPhone(user.getPhone());
            userEntity.setLoginId(user.getLoginId());
            userEntity.setLoginPassword(new String(result));
        }
    }


}
