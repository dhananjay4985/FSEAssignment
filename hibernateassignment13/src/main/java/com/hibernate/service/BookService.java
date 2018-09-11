package com.hibernate.service;

import com.hibernate.bo.Book;
import com.hibernate.daoimpl.BookDAOImpl;

public class BookService {

	private static BookDAOImpl bookDAOImpl;
	
	public BookDAOImpl bookDAOImpl() {
		return bookDAOImpl;
	}
	public BookService() {
		bookDAOImpl = new BookDAOImpl();
	}
	
	public void addBook(Book book) {
		bookDAOImpl.openCurrentSessionWithTransaction();
		bookDAOImpl.addBook(book);
		bookDAOImpl.closeCurrentSessionWithTransaction();	
	}
	
	public Book searchBook(int id) {
		bookDAOImpl.openCurrentSession();
		Book book = bookDAOImpl.searchById(id);
		System.out.println("Book Details :"+book.toString());
		bookDAOImpl.closeCurrentSession();
		return book;
	}
	
	public void deleteBookById(int id) {
		bookDAOImpl.openCurrentSessionWithTransaction();
		bookDAOImpl.deleteById(id);
		bookDAOImpl.closeCurrentSessionWithTransaction();
	}
}
