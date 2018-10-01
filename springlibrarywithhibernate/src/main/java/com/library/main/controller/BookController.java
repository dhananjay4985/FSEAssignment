package com.library.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.library.main.model.Book;
import com.library.main.service.BookService;

@Controller
@RequestMapping(value="/library")		
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	@RequestMapping(value="/allbooks",method = RequestMethod.GET)
	public ModelAndView listAllBooks(ModelAndView model){
		model.addObject(bookService.getBook());
		model.setViewName("getAllBooks");
		return model;
	}	
	@RequestMapping(value="/allbooks/getbook/{bookId}")
	public ModelAndView getBookById(@PathVariable("bookId") Integer bookId,ModelAndView model) {
		model.addObject("book", bookService.findById(bookId));
		model.setViewName("book");
		return model;
	}	
	@RequestMapping(value="/allbooks/update/{bookId}")
	public String updateBook(@PathVariable(value = "bookId") Integer bookId,Model model) {	
		model.addAttribute("book", bookService.findById(bookId));
		model.addAttribute("booklist", bookService.getBook());
		return "book";
	}
	@RequestMapping("/allbooks/add")
	public String createBook(@ModelAttribute Book book) {		
		if(book.getBookId()==0) {
			bookService.createBook(book);
		}else {
			bookService.update(book);
		}
		return "redirect:/getAllBooks";
	}
	@RequestMapping("/allbooks/delete/{bookId}")
	public String deleteBook(@PathVariable(value = "bookId") Integer bookId) {
		bookService.deleteBookById(bookId);
		return "redirect:/getAllBooks";
	}
}
