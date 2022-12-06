package com.manish.spring.controller;

import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testAddUserSuccess(){
        when(userService.addUser(any(User.class))).thenReturn(TestUtil.getAddUserSuccessData());
        UserResponse<String> userResponse= userController.addUser(TestUtil.getUser());
        assertNotNull(userResponse);
        assertNotNull(userResponse.getData());
        assertEquals("U-001",userResponse.getData());
        assertNull(userResponse.getErrorCode());
        assertNull(userResponse.getErrorMessage());
    }

    @Test
    public void testAddUserError(){
        when(userService.addUser(any(User.class))).thenReturn(TestUtil.getAddUserErrorData());
        UserResponse<String> userResponse= userController.addUser(TestUtil.getUser());
        assertNotNull(userResponse);
        assertNull(userResponse.getData());
        assertNotNull(userResponse.getErrorCode());
        assertEquals("ERROR_ADD_USER",userResponse.getErrorCode());
        assertEquals("Unable to add user",userResponse.getErrorMessage());
    }



}