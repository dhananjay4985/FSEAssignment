package com.hibernate.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.bo.Book;
import com.hibernate.bo.Subject;
import com.hibernate.dao.SubjectDAO;

public class SubjectDAOImpl implements SubjectDAO<Subject,Integer> {

	private Session currentSession;
	private Transaction currentTransaction;

	public SubjectDAOImpl() {
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
	public void addSubject(Subject entity) {
		getCurrentSession().save(entity);
	}
	@Override
	public Subject searchById(Integer subjectId) {
		Subject subject = (Subject) getCurrentSession().get(Subject.class, subjectId);
		return subject;
	}
	@Override
	public void deleteById(Integer subjectId) {
		Subject subject = (Subject) getCurrentSession().get(Subject.class, subjectId);
		getCurrentSession().delete(subject);
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

}
