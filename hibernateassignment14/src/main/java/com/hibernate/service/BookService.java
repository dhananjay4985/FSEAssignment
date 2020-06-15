package com.hibernate.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public void sortBookByTitle() {
		bookDAOImpl.openCurrentSession();
		List<Book> bookList = bookDAOImpl.sortAllBooksByTitle();
		bookList = bookList.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
		bookList.forEach(obj -> System.out.println("BookId: "+obj.getBookId()+",Price: "+obj.getPrice()+",Title: "+obj.getTitle()+",Volume: "+obj.getVolume()+",PublishDate: "+obj.getPublishDate()));	
		bookDAOImpl.closeCurrentSession();
	}
	public void sortBookByPublishDate() {
		bookDAOImpl.openCurrentSession();
		List<Book> bookList = bookDAOImpl.sortBookByPublishDate();
		bookList = bookList.stream().sorted(Comparator.comparing(Book::getPublishDate)).collect(Collectors.toList());
		bookList.forEach(obj -> System.out.println("BookId: "+obj.getBookId()+",Price: "+obj.getPrice()+",Title: "+obj.getTitle()+",Volume: "+obj.getVolume()+",PublishDate: "+obj.getPublishDate()));	
		bookDAOImpl.closeCurrentSession();
	}
}
