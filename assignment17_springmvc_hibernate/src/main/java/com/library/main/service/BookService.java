package com.library.main.service;

import java.util.List;

import com.library.main.model.Book;

public interface BookService {

	public void createBook(Book book);
	public List<Book> bookList();
	public Book findById(Integer bookId);
	public void update(Book book);
	public void deleteBookById(Integer bookId);
}
