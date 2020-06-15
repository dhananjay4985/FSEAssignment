package com.library.mainapp.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.library.mainapp.dao.LibraryDAO;
import com.library.mainapp.model.Book;
import com.library.mainapp.model.Subject;

@Component
public class LibraryConsumer {

	@Autowired
	@Qualifier("daoimpl")
	LibraryDAO lib;
	
	public LibraryDAO getLib() {
		return lib;
	}
	public void setLib(LibraryDAO lib) {
		this.lib = lib;
	}
	public void addBookInLibrary(Book book) {
		this.lib.addBook(book);
	}
	public void addSubejctInLibrary(Subject subject) {		
		this.lib.addSubject(subject);
	}
	public void searchBookById(Long bookId) {		
		this.lib.searchBook(bookId);
	}
	public void searchSubjectById(Long subjectId) {		
		this.lib.searchSubject(subjectId);
	}
	public void deleteBookById(Long bookId) {		
		this.lib.deleteBook(bookId);
	}
	public void deketeSubjectById(Long subjectId) {		
		this.lib.deleteSubject(subjectId);
	}
}
