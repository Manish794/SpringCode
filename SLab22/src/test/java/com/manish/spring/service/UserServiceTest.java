package com.manish.spring.service;

import com.manish.spring.controller.TestUtil;
import com.manish.spring.jpa.entity.UserEntity;
import com.manish.spring.model.UserResponse;
import com.manish.spring.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUserWithExistingEmail(){
        //Assumed that record found with matching email id in Database
        when(userRepo.findByEmail(anyString())).thenReturn(TestUtil.getOptionalUserEntity());

        UserResponse<String> response = userService.addUser(TestUtil.getUser());
        assertNotNull(response);
        assertNull(response.getData());
        assertNotNull(response.getErrorCode());
        assertNotNull(response.getErrorMessage());
        assertEquals("EMAIL_ALREADY_USED",response.getErrorCode());
        assertEquals("Email testemail is not available", response.getErrorMessage());

        verify(userRepo, times(1)).findByEmail(anyString());
        verify(userRepo, times(0)).findByPhone(anyLong());
        verify(userRepo, times(0)).findByLoginId(anyString());
        verify(userRepo, times(0)).save(any(UserEntity.class));
    }

    @Test
    public void testAddUserWithExistingPhone(){
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));
        //Assumed that record found with matching phone in Database
        when(userRepo.findByPhone(anyLong())).thenReturn(TestUtil.getOptionalUserEntity());

        UserResponse<String> response = userService.addUser(TestUtil.getUser());
        assertNotNull(response);
        assertNull(response.getData());
        assertNotNull(response.getErrorCode());
        assertNotNull(response.getErrorMessage());
        assertEquals("PHONE_ALREADY_USED",response.getErrorCode());
        assertEquals("Phone 121212 is not available", response.getErrorMessage());

        verify(userRepo, times(1)).findByEmail(anyString());
        verify(userRepo, times(1)).findByPhone(anyLong());
        verify(userRepo, times(0)).findByLoginId(anyString());
        verify(userRepo, times(0)).save(any(UserEntity.class));

    }

    @Test
    public void testAddUserWithExistingLoginId(){
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepo.findByPhone(anyLong())).thenReturn(Optional.ofNullable(null));
        //Assumed that record found with matching phone in Database
        when(userRepo.findByLoginId(anyString())).thenReturn(TestUtil.getOptionalUserEntity());

        UserResponse<String> response = userService.addUser(TestUtil.getUser());
        assertNotNull(response);
        assertNull(response.getData());
        assertNotNull(response.getErrorCode());
        assertNotNull(response.getErrorMessage());
        assertEquals("LOGIN_ID_ALREADY_USED",response.getErrorCode());
        assertEquals("LoginId testlogin is not available", response.getErrorMessage());

        verify(userRepo, times(1)).findByEmail(anyString());
        verify(userRepo, times(1)).findByPhone(anyLong());
        verify(userRepo, times(1)).findByLoginId(anyString());
        verify(userRepo, times(0)).save(any(UserEntity.class));
    }


    @Test
    public void testAddUser(){
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepo.findByPhone(anyLong())).thenReturn(Optional.ofNullable(null));
        when(userRepo.findByLoginId(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepo.save(any(UserEntity.class))).thenReturn(TestUtil.getUserEntity());

        UserResponse<String> response = userService.addUser(TestUtil.getUser());
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("U-001",response.getData());
        assertNull(response.getErrorCode());
        assertNull(response.getErrorMessage());

        verify(userRepo, times(1)).findByEmail(anyString());
        verify(userRepo, times(1)).findByPhone(anyLong());
        verify(userRepo, times(1)).findByLoginId(anyString());
        verify(userRepo, times(1)).save(any(UserEntity.class));

    }

    @Test
    public void testAddUserWithException(){
        when(userRepo.findByEmail(anyString())).thenThrow(new RuntimeException("connection timeout"));

        UserResponse<String> response = userService.addUser(TestUtil.getUser());
        assertNotNull(response);
        assertNull(response.getData());
        assertNotNull(response.getErrorCode());
        assertNotNull(response.getErrorMessage());
        assertEquals("ERROR_ADDING_USER",response.getErrorCode());
        assertEquals("connection timeout", response.getErrorMessage());

        verify(userRepo, times(1)).findByEmail(anyString());
        verify(userRepo, times(0)).findByPhone(anyLong());
        verify(userRepo, times(0)).findByLoginId(anyString());
        verify(userRepo, times(0)).save(any(UserEntity.class));

    }

}