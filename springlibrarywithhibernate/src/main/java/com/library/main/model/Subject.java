package com.library.main.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="subject")
public class Subject {
	
	@Id
	@Column(name="subjectid")
	private Long subjectId;
	
	@Column(name="subjecttitle")
	private String subjectTitle;

	@Column(name="durationinhours")
	private Integer durationInHours;
	
	@OneToMany(fetch = FetchType.EAGER,targetEntity = Book.class ,mappedBy = "subject",orphanRemoval = true,
			cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.REMOVE})
	//@JsonIgnoreProperties("subject")
	private Set<Book> references = new HashSet<Book>();
	
	public Subject() {
		super();
	}
	
	public Subject(Long subjectId, String subjectTitle, Integer durationInHours, Set<Book> references) {
		super();
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
				+ durationInHours + "]";
	}
}
