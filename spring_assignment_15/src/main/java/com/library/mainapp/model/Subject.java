package com.library.mainapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long subjectId;	
	private String subjectTitle;	
	private Integer durationInHours;	
	private Set<Book> references = new HashSet<Book>();

	public Subject() {
		//default
	}
	public Subject(Long subjectId, String subjectTitle, Integer durationInHours, Set<Book> references) {
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.durationInHours = durationInHours;
		this.references = references;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public Integer getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<Book> getReferences() {
		return references;
	}
	public void setReferences(Set<Book> references) {
		this.references = references;
	}
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectTitle=" + subjectTitle + ", durationInHours="
				+ durationInHours + ", references=" + references + "]";
	}
}
