package com.library.dao;

import com.library.bo.Book;
import com.library.bo.Subject;

public interface LibraryDAO {
	
	void addBook(Book book);
	void addSubject(Subject subject);
	void searchBook(long bookId);
	void searchSubject(long subjectId);
	void deleteBook(long bookId);
	void deleteSubject(int subjectId);
	void displayBookByTitle();
	void displayBookByPublishDate();
	void displaySubjectByTitle();
}
