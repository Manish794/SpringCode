package com.manish.spring.repo;

import com.manish.spring.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, String> {

    Optional<BookEntity> findByBookName(String bookName);

}
