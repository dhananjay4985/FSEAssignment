package com.library.main;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.main.model.Subject;
import com.library.main.service.SubjectService;

@Controller
public class SubjectController {

	private SubjectService subjectService;

	@Autowired
	@Qualifier(value = "subjectService")
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@RequestMapping(value = "/allsubjects", method = RequestMethod.GET)
	public String listAllSubjects(Model model){
		model.addAttribute("subject",new Subject());
		model.addAttribute("subjectList", this.subjectService.getSubjectList());
		return "subject";
	}	

	@RequestMapping(value = "/allsubjects/{subjectId}" , method = RequestMethod.GET)
	public String getSubjectById(@PathVariable("subjectId") Long subjectId,Model model) {
		System.out.println("Inside query subject");
		model.addAttribute("subject",new Subject());
		model.addAttribute("subject",this.subjectService.findSubjectById(subjectId));
		return "showsubject";
	}

	@RequestMapping(value = "/allsubjects/add" , method = RequestMethod.POST)
	public String createSubject(@Valid @ModelAttribute("subject") Subject subject,BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Error in subject bindings");   
		}

		if(subject.getSubjectId() == null|| subject.getSubjectId() == 0) {
			System.out.println("subjectID is null");
			this.subjectService.createSubject(subject);
		}else {
			System.out.println("else update....");
			this.subjectService.update(subject);
		}
		return "redirect:/allsubjects";
	}
		
	@RequestMapping(value = "/allsubjects/update/{subjectId}")
	public String updateBook(@PathVariable(value = "subjectId") Long subjectId,Model model) {	
		model.addAttribute("subject", this.subjectService.findSubjectById(subjectId));
		model.addAttribute("subjectlist", this.subjectService.getSubjectList());
		return "subject";
	}

	@RequestMapping(value = "/allsubjects/delete/{subjectId}")
	public String deleteSubject(@PathVariable(value = "subjectId") Long subjectId) {
		System.out.println("inside delete sunject controller");
		this.subjectService.deleteSubjectById(subjectId);
		return "redirect:/allsubjects";
	}
}
