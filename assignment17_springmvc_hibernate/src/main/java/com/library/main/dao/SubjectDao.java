package com.library.main.dao;

import java.util.List;

import com.library.main.model.Subject;

public interface SubjectDao {

	void createSubject(Subject subject);
	List<Subject> getSubjectList();
	Subject findSubjectById(Long subjectId);
	void update(Subject subject);
	void deleteSubjectById(Long subjectId);
}
