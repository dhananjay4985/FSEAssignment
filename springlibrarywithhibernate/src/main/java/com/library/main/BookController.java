package com.library.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class BookController {

	
	private BookService bookService;
	
	@Autowired(required=true)
	@Qualifier(value="bookService")
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/index")
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value ="/allbooks", method = RequestMethod.GET)
	public String listAllBooks(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", bookService.bookList());
		return "book";
	}
	
	@RequestMapping(value="/allbooks/getbook/{bookId}" , method = RequestMethod.GET)
	public ModelAndView getBookById(@PathVariable("bookId") Integer bookId,ModelAndView model) {
		model.addObject("book", bookService.findById(bookId));
		model.setViewName("book");
		return model;
	}	
	
	@RequestMapping(value="/allbooks/update/{bookId}", method = RequestMethod.PUT)
	public String updateBook(@PathVariable(value = "bookId") Integer bookId,Model model) {	
		model.addAttribute("book", bookService.findById(bookId));
		model.addAttribute("bookList", bookService.bookList());
		return "book";
	}
	
	@RequestMapping(value = "/allbooks/add", method = RequestMethod.POST)
	public String createBook(@ModelAttribute("book") Book book) {		
		if(book.getBookId()== null|| book.getBookId() == 0) {
			bookService.createBook(book);
		}else {
			bookService.update(book);
		}
		return "redirect:/allbooks";
	}
	
	@RequestMapping(value = "/allbooks/delete/{bookId}" , method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable(value = "bookId") Integer bookId) {
		bookService.deleteBookById(bookId);
		return "redirect:/allbooks";
	}
}
