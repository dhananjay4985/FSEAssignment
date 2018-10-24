package com.library.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.main.model.Book;


@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	//private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createBook(Book book) {
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("inside create book daoimpl");
		this.sessionFactory.getCurrentSession().saveOrUpdate(book);		
	}

	@Override
	public List<Book> bookList() {
		System.out.println("Inside DAO get List");
		this.sessionFactory.getCurrentSession().clear();
		Session session = this.sessionFactory.getCurrentSession();	
		List<Book> bookList = (List<Book>)session.createQuery("from Book").list();	
		for(Book book : bookList) {
			book.setSubjectId(book.getSubject().getSubjectId());
		}		
		return bookList;
	}

	@Override
	public Book findById(Integer bookId) {
		this.sessionFactory.getCurrentSession().clear();
		Session session = this.sessionFactory.getCurrentSession();		
		Book book = (Book)session.get(Book.class, bookId);
		book.setSubjectId(book.getSubject().getSubjectId());		
		return  book;
	}

	@Override
	public void update(Book book) {
		//this.sessionFactory.getCurrentSession().clear();
		this.sessionFactory.getCurrentSession().update(book);		
	}

	@Override
	public void deleteBookById(Integer bookId) {
		//this.sessionFactory.getCurrentSession().clear();
		Book deleteBook = (Book)sessionFactory.getCurrentSession().load(Book.class,bookId);
		if(deleteBook != null) {
			this.sessionFactory.getCurrentSession().delete(deleteBook);
		}		
	}

}
