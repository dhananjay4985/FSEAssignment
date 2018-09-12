package com.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.hibernate.bo.Book;

public interface BookDAO<T,Id extends Serializable> {
	
	public void addBook(T entity);
	public T searchById(Id bookId);
	public void deleteById(Id BookId);
	public List<T> sortAllBooksByTitle();
	public List<Book> sortBookByPublishDate();
}
