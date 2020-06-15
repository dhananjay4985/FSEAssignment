package com.library.main.dao;

import java.util.List;

import com.library.main.model.Subject;

public interface SubjectDao {

	void createSubject(Subject subject);
	List<Subject> getSubject();
	Subject findById(Long subjectId);
	Subject update(Subject subject, Long subjectId);
	void deleteSubjectById(Long subjectId);
}
