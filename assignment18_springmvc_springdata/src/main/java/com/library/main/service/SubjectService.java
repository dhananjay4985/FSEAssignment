package com.library.main.service;

import java.util.List;

import com.library.main.model.Subject;


public interface SubjectService {

	void createSubject(Subject subject);
	List<Subject> getSubjectList();
	Subject findSubjectById(Long subjectId);
	void update(Subject subject);
	void deleteSubjectById(Long subjectId);
}
