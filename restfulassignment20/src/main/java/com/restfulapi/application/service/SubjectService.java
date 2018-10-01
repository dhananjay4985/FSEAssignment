package com.restfulapi.application.service;

import java.util.List;

import com.restfulapi.application.model.Subject;

public interface SubjectService {

	void createSubject(Subject subject);
	List<Subject> getSubject();
	Subject findById(Long subjectId);
	Subject update(Subject subject, Long subjectId);
	void deleteSubjectById(Long subjectId);
}
