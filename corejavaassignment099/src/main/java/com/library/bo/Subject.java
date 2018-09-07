package com.library.bo;

import java.io.Serializable;
import java.util.Set;

public class Subject implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int subjectId;
	private String subjectTitle;
	private int durationInHours;
	private Set<Book> references;
	
	public Subject(int subjectId,String subjectTitle,int durationInHours,Set<Book> bookSet) {
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.durationInHours = durationInHours;
		this.references = bookSet;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}	
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<Book> getBookSet() {
		return references;
	}
	public void setBookSet(Set<Book> bookSet) {
		this.references = bookSet;
	}
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subjectTitle + ", durationInHours=" + durationInHours
				+ ", bookSet=" + references + "]";
	}

}
