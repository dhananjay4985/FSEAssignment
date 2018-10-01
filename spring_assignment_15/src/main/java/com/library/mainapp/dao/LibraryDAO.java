package com.library.mainapp.dao;

import com.library.mainapp.model.Book;
import com.library.mainapp.model.Subject;

public interface LibraryDAO {
	
	void addBook(Book book);
	void addSubject(Subject subject);
	void searchBook(Long bookId);
	void searchSubject(Long subjectId);
	void deleteBook(Long bookId);
	void deleteSubject(Long subjectId);
}
