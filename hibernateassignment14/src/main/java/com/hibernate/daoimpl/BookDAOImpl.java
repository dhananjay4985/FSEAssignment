package com.hibernate.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.bo.Book;
import com.hibernate.bo.Subject;
import com.hibernate.dao.BookDAO;

public class BookDAOImpl implements BookDAO<Book,Integer> {

	private Session currentSession;
	private Transaction currentTransaction;

	public BookDAOImpl() {
		//default constructor
	}
	public Session getCurrentSession() {
		return currentSession;
	}
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}	
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}
	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	
	@Override
	public void addBook(Book entity) {
		getCurrentSession().save(entity);
	}
	@Override
	public Book searchById(Integer bookId) {
		Book book = (Book) getCurrentSession().get(Book.class, bookId);
		return book;
	}
	@Override
	public void deleteById(Integer bookId) {
		Book book = (Book) getCurrentSession().get(Book.class, bookId);
		getCurrentSession().delete(book);
	}

	private static SessionFactory getSessionFactory() {
		Configuration config = new Configuration().configure();
		config.addAnnotatedClass(Book.class);
		config.addAnnotatedClass(Subject.class);
		SessionFactory sessionFactory = config.buildSessionFactory();
		return sessionFactory;
	}
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}
	public Session openCurrentSessionWithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	public void closeCurrentSessionWithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	@Override
	public List<Book> sortAllBooksByTitle() {
		List<Book> list = (List<Book>)getCurrentSession().createQuery("from Book").list();
		return list;
	}
	@Override
	public List<Book> sortBookByPublishDate() {
		List<Book> list = (List<Book>)getCurrentSession().createQuery("from Book").list();
		return list;
	}
	
}
