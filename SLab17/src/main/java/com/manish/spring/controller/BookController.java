package com.manish.spring.controller;

import com.manish.spring.model.UserResponse;
import com.manish.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        System.out.println("BookController()");
        this.bookService = bookService;
    }

    @GetMapping(value="/addtocart/{bookName}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<List<String>> addBookToCart(
            @RequestHeader(name = "userId") String userId,
            @PathVariable("bookName") String bookName){
        System.out.println("BookController -> addBookToCard "+bookName);
        return bookService.addBookToCart(bookName, userId);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<List<String>> getCartItems( @RequestHeader(name = "userId") String userId){
        System.out.println("BookController -> showCart");
        return bookService.getCartItems(userId);
    }


}
