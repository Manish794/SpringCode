package com.manish.spring.transformer;

import com.manish.spring.jpa.entity.BookEntity;
import com.manish.spring.model.Book;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class BookTransformer {

    public static BookEntity getBookEntity(Book book){
        if(!ObjectUtils.isEmpty(book)){
            BookEntity bookEntity = BookEntity
                    .builder()
                    .bookId(book.getBookId())
                    .bookName(book.getBookName())
                    .build();
            return bookEntity;
        }
        return null;
    }

    public static Book getBook(BookEntity bookEntity){
        if(!ObjectUtils.isEmpty(bookEntity)){
            Book book = Book
                    .builder()
                    .bookId(bookEntity.getBookId())
                    .bookName(bookEntity.getBookName())
                    .build();
            return book;
        }
        return null;
    }

    public static List<Book> getBooks(List<BookEntity> bookEntities){
        if(!CollectionUtils.isEmpty(bookEntities)) {
            List<Book> users = new ArrayList<>(bookEntities.size());
            for(BookEntity entity : bookEntities){
                users.add(getBook(entity));
            }
            return users;
        }
        return null;
    }

}
