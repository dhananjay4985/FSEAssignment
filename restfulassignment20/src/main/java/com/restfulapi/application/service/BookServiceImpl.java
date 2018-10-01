package com.restfulapi.application.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfulapi.application.exceptionhandler.ResourceNotFoundException;
import com.restfulapi.application.model.Book;
import com.restfulapi.application.repository.BookRepository;
import com.restfulapi.application.repository.SubjectRepository;

@Service
public class BookServiceImpl implements BookService {


	//@Autowired
	private BookRepository bookRepository;

	//@Autowired
	private SubjectRepository subjectRepository;


	@Autowired
	public BookServiceImpl(BookRepository bookRepository, SubjectRepository subjectRepository) {
		this.bookRepository = bookRepository;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public void createBook(Book saveBook) {
		//adding subject id in book table
	    saveBook.setSubject(subjectRepository.getOne(saveBook.getSubjectId()));
		bookRepository.save(saveBook);
	}

	@Override
	public Book findById(Integer id) {		
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));
		book.setSubjectId(book.getSubject().getSubjectId());
		return book;
	}

	@Override
	public List<Book> getBook() {
		List<Book> bookList = bookRepository.findAll();
		for(Book book : bookList) {
			book.setSubjectId(book.getSubject().getSubjectId());
		}
		return bookList; 
	}

	@Override
	public Book update(Book updateBook, Integer bookId) {		
		Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","BookIs",bookId));
		book.setBookId(updateBook.getBookId());
		book.setPrice(updateBook.getPrice());
		book.setPublishDate(updateBook.getPublishDate());
		book.setTitle(updateBook.getTitle());
		book.setVolume(updateBook.getVolume());		
		book.setSubject(subjectRepository.getOne(updateBook.getSubjectId()));
		Book updatedBook = bookRepository.save(book);
		return updatedBook;
	}

	@Override
	public void deleteBookById(Integer bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
		bookRepository.delete(book);
	}
}
