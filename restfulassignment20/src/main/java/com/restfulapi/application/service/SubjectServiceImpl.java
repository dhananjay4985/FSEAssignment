package com.restfulapi.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfulapi.application.exceptionhandler.ResourceNotFoundException;
import com.restfulapi.application.model.Subject;
import com.restfulapi.application.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	private SubjectRepository subjectRepository;
	
	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository) {		
		this.subjectRepository = subjectRepository;
	}

	@Override
	public Subject createSubject(Subject subject) {	
		System.out.println("ssss "+subject.toString());
		subjectRepository.save(subject);
		return subject;
	}

	@Override
	public List<Subject> getSubject() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject findById(Long subjectId) {
		return subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", subjectId));
	}

	@Override
	public Subject update(Subject subject, Long subjectId) {
		
		return null;
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		
	}

}
