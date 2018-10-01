package com.library.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.dao.BookDao;
import com.library.main.model.Book;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	@Transactional
	public void createBook(Book book) {
		bookDao.createBook(book);
	}

	@Override
	@Transactional
	public List<Book> getBook() {
		return bookDao.getBook();
	}

	@Override
	@Transactional
	public Book findById(Integer bookId) {
		return bookDao.findById(bookId);
	}

	@Override
	@Transactional
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	@Transactional
	public void deleteBookById(Integer bookId) {
		bookDao.deleteBookById(bookId);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
