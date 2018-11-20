package user.library.librarymanagementusingbasicauth.service;

import java.util.List;

import user.library.librarymanagementusingbasicauth.model.Subject;


public interface SubjectService {

	void createSubject(Subject subject);
	List<Subject> getSubject();
	Subject findById(Long subjectId);
	Subject update(Subject subject, Long subjectId);
	void deleteSubjectById(Long subjectId);
}
