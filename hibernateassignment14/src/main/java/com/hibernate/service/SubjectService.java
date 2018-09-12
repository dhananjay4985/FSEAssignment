package com.hibernate.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	public void sortSubjectByTitle() {
		subjectDAOImpl.openCurrentSession();
		List<Subject> subjectList = subjectDAOImpl.sortAllSubjectByTitle();
		subjectList = subjectList.stream().sorted(Comparator.comparing(Subject::getSubjectTitle)).collect(Collectors.toList());
		subjectList.forEach(obj -> System.out.println("SubjectId: "+obj.getSubjectId()+",Title: "+obj.getSubjectTitle()+",Duration: "+obj.getDurationInHours()+"Hrs"+",Book:" +obj.getReferences().toString()));
		subjectDAOImpl.closeCurrentSession();
	}

}
