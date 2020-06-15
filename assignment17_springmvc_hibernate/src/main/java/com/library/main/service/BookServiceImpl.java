package com.library.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.dao.BookDao;
import com.library.main.dao.SubjectDao;
import com.library.main.model.Book;

@Service("bookService")
@Transactional//(propagation = Propagation.SUPPORTS,readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired(required = true)
	@Qualifier(value = "bookDao")
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "subjectDao")
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	@Override
	@Transactional
	public void createBook(Book book) {
		System.out.println("Inside creatie book");
		book.setSubject(subjectDao.findSubjectById(book.getSubjectId()));
		this.bookDao.createBook(book);
	}

	@Override
	@Transactional
	public List<Book> bookList() {
		return this.bookDao.bookList();
	}

	@Override
	@Transactional
	public Book findById(Integer bookId) {
		return this.bookDao.findById(bookId);
		//System.out.println("Book Found "+book.toString());
		//return book;
	}

	@Override
	@Transactional
	public void update(Book updatedBook) {
		System.out.println("Inside update serice impl");
		//book.setSubjectId(book.getSubject().getSubjectId());
		Book existingBook = bookDao.findById(updatedBook.getBookId());
		existingBook.setBookId(updatedBook.getBookId());
		existingBook.setTitle(updatedBook.getTitle());
		existingBook.setPrice(updatedBook.getPrice());
		existingBook.setVolume(updatedBook.getVolume());
		existingBook.setPublishDate(updatedBook.getPublishDate());
		existingBook.setSubject(subjectDao.findSubjectById(updatedBook.getSubjectId()));		
		this.bookDao.update(existingBook);
	}

	@Override
	@Transactional
	public void deleteBookById(Integer bookId) {
		this.bookDao.deleteBookById(bookId);
	}

	
}
