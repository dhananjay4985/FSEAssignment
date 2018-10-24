package com.library.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.main.model.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Long> {

}
