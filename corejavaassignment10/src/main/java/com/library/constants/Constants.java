package com.library.constants;

public class Constants {

	public static final String INSERT_BOOK        = "INSERT INTO BOOK "+"(bookid,title,price,volume,publishdate,subid) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_ONLYBOOK    = "INSERT INTO BOOK "+"(bookid,title,price,volume,publishdate) VALUES (?, ?, ?, ?, ?)";
	public static final String INSERT_SUBJECT     = "INSERT INTO SUBJECT "+"(subjectid,subjecttitle,durationinhours) VALUES (?, ?, ?)";
	public static final String DELETE_BOOK        = "DELETE FROM BOOK WHERE BOOK.BOOKID = ?";
	public static final String DELETE_SUBBOOK     = "DELETE FROM BOOK WHERE BOOK.SUBID = ?";
	public static final String DELETE_SUBJECT     = "DELETE FROM SUBJECT WHERE SUBJECT.SUBJECTID = ?";
	public static final String SEARCH_SUBJECT     = "SELECT * FROM SUBJECT,BOOK WHERE SUBJECT.SUBJECTID = BOOK.SUBID AND SUBJECT.SUBJECTID = ?";
	public static final String SEARCH_BOOK        = "SELECT * from BOOK WHERE BOOKID = ?";
	public static final String SELECT_ALL_BOOK    = "SELECT * from BOOK";
	public static final String SELECT_ALL_SUBJECT = "SELECT * FROM SUBJECT,BOOK WHERE SUBJECT.SUBJECTID = BOOK.SUBID AND SUBJECT.SUBJECTID";
}
