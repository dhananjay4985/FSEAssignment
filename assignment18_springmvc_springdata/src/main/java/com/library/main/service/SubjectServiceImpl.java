package com.library.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.model.Subject;
import com.library.main.repository.BookRepository;
import com.library.main.repository.SubjectRepository;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional
	public void createSubject(Subject subject) {
		//subject.setReferences(references);
		subjectRepository.save(subject);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectList() {
		return subjectRepository.findAll();
	}

	@Override
	@Transactional
	public Subject findSubjectById(Long subjectId) {		
		return subjectRepository.getOne(subjectId);
	}

	@Override
	@Transactional
	public void update(Subject subject) {
		Subject oldSubject = subjectRepository.getOne(subject.getSubjectId());
		oldSubject.setSubjectId(subject.getSubjectId());
		oldSubject.setSubjectTitle(subject.getSubjectTitle());
		oldSubject.setDurationInHours(subject.getDurationInHours());
		oldSubject.setReferences(subject.getReferences());
		subjectRepository.save(oldSubject);
	}

	@Override
	@Transactional
	public void deleteSubjectById(Long subjectId) {
		subjectRepository.delete(subjectId);
	}
}
