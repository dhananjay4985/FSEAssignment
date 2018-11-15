package com.restfulapi.application.service;

import java.util.List;

import com.restfulapi.application.model.Subject;

public interface SubjectService {

	public Subject createSubject(Subject subject);
	public List<Subject> getSubject();
	public Subject findById(Long subjectId);
	public Subject update(Subject subject, Long subjectId);
	public void deleteSubjectById(Long subjectId);
}
