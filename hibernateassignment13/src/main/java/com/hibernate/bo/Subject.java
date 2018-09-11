package com.hibernate.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="subject_id")
	private int subjectId;

	@Column(name="subjecttitle")
	private String subjectTitle;

	@Column(name="durationinhours")
	private int durationInHours;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "subject_book", joinColumns = { @JoinColumn(name = "subject_id") }, inverseJoinColumns = { @JoinColumn(name = "book_id") })
	private Set<Book> references = new HashSet<Book>();

	public Subject() {
		//default constructor
	}
	public Subject(int subjectId, String subjectTitle, int durationInHours, Set<Book> references) {
		super();
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.durationInHours = durationInHours;
		this.references = references;
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
	public Set<Book> getReferences() {
		return references;
	}
	public void setReferences(Set<Book> references) {
		this.references = references;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subjectTitle + ", durationInHours=" + durationInHours
				+ ", bookSet=" + references + "]";
	}
}
