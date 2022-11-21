package com.manish.spring.service;

import com.manish.spring.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private static Map<String, List<String>> cart = new HashMap<>();

    public UserResponse<List<String>> addBookToCart(String bookName, String userId){
        UserResponse<List<String>> response = new UserResponse<>();
        List<String> books = cart.get(userId);
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(bookName);
        cart.put(userId, books);
        response.setData(books);
        return response;
    }

    public UserResponse<List<String>> getCartItems(String userId){
        UserResponse<List<String>> response = new UserResponse<>();
        List<String> books = cart.get(userId);
        response.setData(books);
        return response;
    }

}
