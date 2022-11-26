package com.manish.spring.repo;

import com.manish.spring.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(long phone);
    Optional<UserEntity> findByLoginId(String loginId);
    Optional<UserEntity> findByLoginIdAndLoginPassword(String loginId, String password);
    List<UserEntity> findAllByEmailOrPhoneOrLoginIdAndUserIdNot(String email, long phone, String loginId, String userId);

}
