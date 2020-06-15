package user.library.librarymanagementusingbasicauth.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.library.librarymanagementusingbasicauth.model.Subject;
import user.library.librarymanagementusingbasicauth.repository.SubjectRepository;
import user.library.librarymanagementusingbasicauth.service.SubjectService;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public void createSubject(Subject subject) {	
		subjectRepository.save(subject);
	}

	@Override
	public List<Subject> getSubject() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject findById(Long subjectId) {
		return subjectRepository.getOne(subjectId);
	}

	@Override
	public Subject update(Subject subject, Long subjectId) {
		
		return null;
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		
	}

}
