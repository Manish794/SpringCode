package com.manish.spring.repo;

import com.manish.spring.domain.Login;
import com.manish.spring.domain.User;
import com.manish.spring.generator.UserIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {

    private UserIdGenerator userIdGenerator;
    private List<User> users = new ArrayList<>();

    @Autowired
    public UserRepo(UserIdGenerator userIdGenerator){
        this.userIdGenerator = userIdGenerator;
    }

    public String addUser(User user){
        user.setUserId(userIdGenerator.generateUserId(users));
        users.add(user);
        return user.getUserId();
    }

    public User updateUser(User newUserData){
        Optional<User> userOptional= users.stream().filter(user -> user.getUserId().equals(newUserData.getUserId())).findFirst();
        if(userOptional.isPresent()){
            User oldUser = userOptional.get();
            oldUser.setEmail(newUserData.getEmail());
            oldUser.setFullName(newUserData.getFullName());
            oldUser.setPhone(newUserData.getPhone());
            oldUser.setLoginId(newUserData.getLoginId());
            oldUser.setLoginPassword(newUserData.getLoginPassword());
            return oldUser;
        }
        return null;
    }

    public User getUser(String userId){
        Optional<User> userOptional= users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    public User getUserByPhone(long phone){
        Optional<User> userOptional= users.stream().filter(user -> user.getPhone() == phone).findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional= users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    public User getUserByLoginId(String loginId){
        Optional<User> userOptional= users.stream().filter(user -> user.getLoginId().equals(loginId)).findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User deleteUser(String userId){
        Optional<User> userOptional= users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
        if(userOptional.isPresent()){
            User user  = userOptional.get();
            users.remove(user);
            return user;
        }
        return null;
    }

    public User loginUser(Login login){
        Optional<User> userOptional= users
                .stream()
                .filter(
                        user ->
                                user.getLoginId().equals(login.getLoginId())
                                        && user.getLoginPassword().equals(login.getLoginPassword())
                )
                .findFirst();

        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }
}
