package com.manish.spring.controller;

import com.manish.spring.model.Book;
import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.service.BookService;
import com.manish.spring.service.UserService;
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

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<String> addBook(@RequestBody Book book){
        System.out.println("BookController -> addBook "+book);
        return bookService.addBook(book);
    }



    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse<List<Book>> getAllBooks(){
        System.out.println("BookController -> getAllBooks");
        return bookService.getAllBooks();
    }

}
