package com.library.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.main.model.Subject;

@Repository("subjectDao")
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createSubject(Subject subject) {
		System.out.println("inside create subejct daoimpl");
		this.sessionFactory.getCurrentSession().merge(subject);
		//this.sessionFactory.getCurrentSession().saveOrUpdate(subject);

	}

	@Override
	public List<Subject> getSubjectList() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Subject> subjectList = (List<Subject>)session.createQuery("from Subject").list();
		return subjectList;
	}

	@Override
	public Subject findSubjectById(Long subjectId) {
		return (Subject)this.sessionFactory.getCurrentSession().get(Subject.class, subjectId);
	}

	@Override
	public void update(Subject subject) {
		this.sessionFactory.getCurrentSession().update(subject);
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		this.sessionFactory.getCurrentSession().delete(subjectId);
	}

}
