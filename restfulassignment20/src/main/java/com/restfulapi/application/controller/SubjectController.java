package com.restfulapi.application.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulapi.application.model.Subject;
import com.restfulapi.application.service.SubjectService;

@RestController
@RequestMapping(value="/library")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class SubjectController {

	
	private SubjectService subjectService;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {		
		this.subjectService = subjectService;
	}
	
	@GetMapping(value="/allSubjects")
	public List<Subject> listAllSubjects(){		;
	return subjectService.getSubject();
	}	
	@GetMapping(value="/allSubjects/{subjectId}")
	public Subject getSubjectById(@PathVariable("subjectId") Long subjectId) {
		return subjectService.findById(subjectId);
	}	
	@PutMapping(value="/allSubjects/{subjectId}")
	public Subject updateSubject(@PathVariable(value = "subjectId") Long subjectId,@Valid @RequestBody Subject subjectDetails) {
		return subjectService.update(subjectDetails, subjectId);

	}
	@PostMapping("/allSubjects")
	public ResponseEntity<Void> createSubject(@Valid @RequestBody Subject subject) {
		System.out.println("Subject ::"+subject.toString());
		subjectService.createSubject(subject);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subjectId}").buildAndExpand(subject.getSubjectId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/allSubjects/{subjectId}")
	public ResponseEntity<Void> deleteSubjects(@PathVariable(value = "subjectId") Long subjectId) {
		subjectService.deleteSubjectById(subjectId);
		return ResponseEntity.ok().build();
	}
}
