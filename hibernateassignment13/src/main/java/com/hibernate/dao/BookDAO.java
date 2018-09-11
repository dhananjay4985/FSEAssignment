package com.hibernate.dao;

import java.io.Serializable;

public interface BookDAO<T,Id extends Serializable> {
	
	public void addBook(T entity);
	public T searchById(Id bookId);
	public void deleteById(Id BookId);
}
