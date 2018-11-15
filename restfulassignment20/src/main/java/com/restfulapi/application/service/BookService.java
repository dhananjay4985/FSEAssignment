package com.restfulapi.application.service;

import java.util.List;

import com.restfulapi.application.model.Book;

public interface BookService {

	public Book createBook(Book book);
	public List<Book> getBook();
	public Book findById(Integer bookId);
	public Book update(Book user, Integer bookId);
	public void deleteBookById(Integer bookId);
}
