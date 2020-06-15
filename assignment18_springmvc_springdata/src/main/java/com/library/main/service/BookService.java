package com.library.main.service;

import java.util.List;

import com.library.main.model.Book;

public interface BookService {
	public void createBook(Book book);
	public List<Book> getBook();
	public Book findById(Long bookId);
	public void update(Book user, Long bookId);
	public void deleteBookById(Long bookId);
}
