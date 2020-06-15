package com.library.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.dao.BookDao;
import com.library.main.dao.SubjectDao;
import com.library.main.model.Subject;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired(required = true)
	@Qualifier("subjectDao")
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	@Autowired(required = true)
	@Qualifier("bookDao")
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	@Transactional
	public void createSubject(Subject subject) {
		//subject.setReferences(references);
		this.subjectDao.createSubject(subject);
		
	}

	@Override
	@Transactional
	public List<Subject> getSubjectList() {
		return this.subjectDao.getSubjectList();
	}

	@Override
	@Transactional
	public Subject findSubjectById(Long subjectId) {		
		return this.subjectDao.findSubjectById(subjectId);
	}

	@Override
	@Transactional
	public void update(Subject subject) {
		this.subjectDao.update(subject);
	}

	@Override
	@Transactional
	public void deleteSubjectById(Long subjectId) {
		this.subjectDao.deleteSubjectById(subjectId);
	}
}
