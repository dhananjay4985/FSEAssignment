package com.library.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.main.model.Book;
import com.library.main.repository.BookRepository;
import com.library.main.repository.SubjectRepository;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public void createBook(Book saveBook) {
		//adding subject id in book table
	    saveBook.setSubject(subjectRepository.getOne(saveBook.getSubjectId()));
		bookRepository.save(saveBook);
	}

	@Override
	public Book findById(Long id) {		
		Book book = bookRepository.findOne(id);//.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));
		book.setSubjectId(book.getSubject().getSubjectId());
		return book;
	}

	@Override
	public List<Book> getBook() {
		List<Book> bookList = bookRepository.findAll();
		for(Book book : bookList) {
			book.setSubjectId(book.getSubject().getSubjectId());
		}
		return bookList; 
	}

	@Override
	public void update(Book updateBook, Long bookId) {		
		Book book = bookRepository.findOne(bookId);//.orElseThrow(()-> new ResourceNotFoundException("Book","BookIs",bookId));
		book.setBookId(updateBook.getBookId());
		book.setPrice(updateBook.getPrice());
		book.setPublishDate(updateBook.getPublishDate());
		book.setTitle(updateBook.getTitle());
		book.setVolume(updateBook.getVolume());		
		book.setSubject(subjectRepository.getOne(updateBook.getSubjectId()));
		bookRepository.save(book);
	}

	@Override
	public void deleteBookById(Long bookId) {
		Book book = bookRepository.findOne(bookId);//.orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
		bookRepository.delete(book);
	}
}
