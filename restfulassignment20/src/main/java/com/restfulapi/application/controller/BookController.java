package com.restfulapi.application.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restfulapi.application.model.Book;
import com.restfulapi.application.service.BookService;


@CrossOrigin(origins= "*",allowedHeaders="*")
@RestController
@RequestMapping(value="/library")		
public class BookController {

	private BookService bookService;
		
	@Autowired
	public BookController(BookService bookService) {		
		this.bookService = bookService;
	}
	@GetMapping(value="/allbooks")
	public List<Book> listAllBooks(){		
		return bookService.getBook();
	}	
	@GetMapping(value="/allbooks/{bookId}")
	public Book getBookById(@PathVariable("bookId") Integer bookId) {
		return bookService.findById(bookId);
	}	
	@PutMapping(value="/allbooks/{bookId}")
	public Book updateBook(@PathVariable(value = "bookId") Integer bookId,@Valid @RequestBody Book bookDetails) {	
		return bookService.update(bookDetails, bookId);
	}
	@PostMapping("/allbooks")
	public ResponseEntity<Void> createBook(@Valid @RequestBody Book book) {		
		bookService.createBook(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookId}").buildAndExpand(book.getBookId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/allbooks/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable(value = "bookId") Integer bookId) {
		bookService.deleteBookById(bookId);
		return ResponseEntity.ok().build();
	}
}
