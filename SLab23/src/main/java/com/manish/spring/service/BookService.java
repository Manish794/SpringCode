package com.manish.spring.service;

import com.manish.spring.jpa.entity.BookEntity;
import com.manish.spring.model.Book;
import com.manish.spring.model.UserResponse;
import com.manish.spring.repo.BookRepo;
import com.manish.spring.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    private BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;

    }

    public UserResponse<String> addBook(Book book){
        UserResponse<String> response = new UserResponse<>();
        try {
            Optional<BookEntity> optionalEntity = bookRepo.findByBookName(book.getBookName());
            if (!optionalEntity.isPresent()) {
                BookEntity entity = bookRepo.save(BookTransformer.getBookEntity(book));
                response.setData(entity.getBookId());
            } else {
                response.setErrorCode("BOOK_ALREADY_AVAILABLE");
                response.setErrorMessage("Book " + book.getBookName() + " is available");
            }
        }catch(Exception e){
            response.setErrorCode("ERROR_ADDING_BOOKS");
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }


    public UserResponse<List<Book>> getAllBooks(){
        UserResponse<List<Book>> response = new UserResponse<>();
        List<BookEntity> bookEntities = bookRepo.findAll();
        if(bookEntities == null || bookEntities.isEmpty()) {
            response.setErrorCode("BOOKS_NOT_FOUND");
            response.setErrorMessage("No books available");
        } else {
            response.setData(BookTransformer.getBooks(bookEntities));
        }
        return response;
    }


}
