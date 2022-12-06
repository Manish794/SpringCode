package com.manish.spring.transformer;

import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserTransformer {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserTransformer(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getUserEntity(User user){
        if(!ObjectUtils.isEmpty(user)){
            UserEntity userEntity = UserEntity
                    .builder()
                    .userId(user.getUserId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .loginId(user.getLoginId())
                    .loginPassword(passwordEncoder.encode(user.getLoginPassword()))
                    .role(user.getRole())
                    .build();
            return userEntity;
        }
        return null;
    }

    public User getUser(UserEntity userEntity){
        if(!ObjectUtils.isEmpty(userEntity)){
            User user = User
                    .builder()
                    .userId(userEntity.getUserId())
                    .fullName(userEntity.getFullName())
                    .email(userEntity.getEmail())
                    .phone(userEntity.getPhone())
                    .loginId(userEntity.getLoginId())
                    .loginPassword(userEntity.getLoginPassword())
                    .role(userEntity.getRole())
                    .build();
            return user;
        }
        return null;
    }

    public List<User> getUsers(List<UserEntity> userEntities){
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
                        .role(userEntity.getRole())
                        .build();
                users.add(user);
            }
            return users;
        }
        return null;
    }

    public void updateUserEntity(UserEntity userEntity, User user){
        if(!ObjectUtils.isEmpty(userEntity) && !ObjectUtils.isEmpty(user)){
            userEntity.setUserId(user.getUserId());
            userEntity.setFullName(user.getFullName());
            userEntity.setEmail(user.getEmail());
            userEntity.setPhone(user.getPhone());
            userEntity.setLoginId(user.getLoginId());
            userEntity.setLoginPassword(passwordEncoder.encode(user.getLoginPassword()));
            userEntity.setRole(user.getRole());
        }
    }


}
