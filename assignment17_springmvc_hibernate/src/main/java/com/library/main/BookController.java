package com.library.main;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.main.model.Book;
import com.library.main.model.Subject;
import com.library.main.service.BookService;

@Controller
//@RequestMapping("/")
public class BookController {

	private BookService bookService;

	@Autowired(required = true)
	@Qualifier(value = "bookService")
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = {"/","/allbooks"}, method = RequestMethod.GET)
	public String listAllBooks(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("bookList", this.bookService.bookList());
		return "book";
	}

	@RequestMapping(value = "/allbooks/{bookId}" , method = RequestMethod.GET)
	public String getBookById(@PathVariable("bookId") Integer bookId,Model model) {
		model.addAttribute("book",new Book());
		model.addAttribute("book",this.bookService.findById(bookId));
		return "showbook";
	}
	
	@RequestMapping(value = "/allbooks/add", method = RequestMethod.POST)
	public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result
			,final RedirectAttributes redirectAttributes) {		
		if (result.hasErrors()) {			 
			System.out.println("Error in bindings"+result.getAllErrors().toString());   
			//return "registration";
		}	
		redirectAttributes.addFlashAttribute("css", "success");
		if(book.getBookId() == null|| book.getBookId() == 0) {
			System.out.println(book.toString());
			this.bookService.createBook(book);
			redirectAttributes.addFlashAttribute("msg", "Book added successfully!");
			
		}else {
			System.out.println("else update....");
			this.bookService.update(book);
			redirectAttributes.addFlashAttribute("msg", "Book updated successfully!");
		}
		return "redirect:/allbooks";
	}

	@RequestMapping(value = "/allbooks/update/{bookId}")
	public String updateBook(@PathVariable(value = "bookId") Integer bookId,Model model) {	
		model.addAttribute("book",this. bookService.findById(bookId));
		model.addAttribute("bookList", this.bookService.bookList());
		return "book";
	}

	@RequestMapping(value = "/allbooks/delete/{bookId}")
	public String deleteBook(@PathVariable(value = "bookId") Integer bookId,final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Book Deleted Successfully!");
		this.bookService.deleteBookById(bookId);
		return "redirect:/allbooks";
	}
}
