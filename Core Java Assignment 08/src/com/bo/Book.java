package com.bo;

import java.io.Serializable;

public class Book implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String bookName;
	private String author;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
		
}
