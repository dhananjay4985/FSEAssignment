package com.library.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.model.Subject;


@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Override
	public void createSubject(Subject subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Subject> getSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject findById(Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		
	}

	

}
