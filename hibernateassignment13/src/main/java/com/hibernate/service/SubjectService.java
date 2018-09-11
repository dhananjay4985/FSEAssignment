package com.hibernate.service;

import com.hibernate.bo.Subject;
import com.hibernate.daoimpl.SubjectDAOImpl;

public class SubjectService {
private static SubjectDAOImpl subjectDAOImpl;
	
	public SubjectDAOImpl subjectDAOImpl() {
		return subjectDAOImpl;
	}
	public SubjectService() {
		subjectDAOImpl = new SubjectDAOImpl();
	}
	
	public void addSubejct(Subject subject) {		
		subjectDAOImpl.openCurrentSessionWithTransaction();
		subjectDAOImpl.addSubject(subject);
		subjectDAOImpl.closeCurrentSessionWithTransaction();
	}
	
	public Subject searchSubject(int id) {
		subjectDAOImpl.openCurrentSession();
		Subject subject = subjectDAOImpl.searchById(id);
		System.out.println("Subject Details :"+subject.toString());
		subjectDAOImpl.closeCurrentSession();
		return subject;
	}
	
	public void deleteSubjectById(int id) {
		subjectDAOImpl.openCurrentSessionWithTransaction();
		subjectDAOImpl.deleteById(id);
		subjectDAOImpl.closeCurrentSessionWithTransaction();
	}

}
