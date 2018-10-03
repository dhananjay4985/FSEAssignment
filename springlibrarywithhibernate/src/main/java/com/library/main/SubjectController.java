package com.library.main;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.main.model.Subject;
import com.library.main.service.SubjectService;

@Controller
@RequestMapping(value="/library")
public class SubjectController {

	private SubjectService subjectService;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {		
		this.subjectService = subjectService;
	}
	
	@RequestMapping(value = "/allSubjects" , method = RequestMethod.GET)
	public List<Subject> listAllSubjects(){		;
	return subjectService.getSubject();
	}	
	@RequestMapping(value = "/allSubjects/{subjectId}", method = RequestMethod.GET)
	public Subject getSubjectById(@PathVariable("subjectId") Long subjectId) {
		return subjectService.findById(subjectId);
	}	
	
	@RequestMapping(value = "/allSubjects/update/{subjectId}" , method = RequestMethod.PUT)
	public String updateBook(@PathVariable(value = "subjectId") Long subjectId,Model model) {	
		model.addAttribute("subject", subjectService.findById(subjectId));
		model.addAttribute("subjectlist", subjectService.getSubject());
		return "subject";
	}
	@RequestMapping(value = "/allSubjects" , method = RequestMethod.POST)
	public void createSubject(@RequestBody Subject subject) {
		subjectService.createSubject(subject);
	}
	@RequestMapping(value = "/allSubjects/{subjectId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubjects(@PathVariable(value = "subjectId") Long subjectId) {
		subjectService.deleteSubjectById(subjectId);
		return ResponseEntity.ok().build();
	}
}
