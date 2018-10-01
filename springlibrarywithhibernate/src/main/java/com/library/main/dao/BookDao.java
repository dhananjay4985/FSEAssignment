package com.library.main.dao;

import java.util.List;

import com.library.main.model.Book;


public interface BookDao {

	public void createBook(Book book);
	public List<Book> getBook();
	public Book findById(Integer bookId);
	public void update(Book book);
	public void deleteBookById(Integer bookId);
}
