package com.library.main.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.main.model.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createBook(Book book) {
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}

	@Override
	public List<Book> getBook() {
		sessionFactory.getCurrentSession().createQuery("from book").list();
		return null;
	}

	@Override
	public Book findById(Integer bookId) {
		return (Book)sessionFactory.getCurrentSession().get(Book.class, bookId);
	}

	@Override
	public void update(Book book) {
		sessionFactory.getCurrentSession().update(book);
		//return book;
	}

	@Override
	public void deleteBookById(Integer bookId) {
		Book deleteBook = (Book)sessionFactory.getCurrentSession().load(Book.class,bookId);
		if(deleteBook != null) {
			this.sessionFactory.getCurrentSession().delete(deleteBook);
		}
	}

}
