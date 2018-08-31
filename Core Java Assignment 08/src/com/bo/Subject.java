package com.bo;

import java.io.Serializable;
import java.util.Set;

public class Subject implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private long subjectId;
	private String subtitle;
	private int durationInHours;
	private Set<Book> references;
	
	public Subject(long subjectId,String subtitle,int durationInHours,Set<Book> bookSet) {
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
		this.references = bookSet;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", bookSet=" + references + "]";
	}

}
