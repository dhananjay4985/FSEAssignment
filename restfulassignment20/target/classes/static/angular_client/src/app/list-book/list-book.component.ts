import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from  '../shared/models/book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.css']
})
export class ListBookComponent implements OnInit {

private books: Book[];

  constructor(private bookService: BookService,private router: Router) { }

  ngOnInit() {
	  this.bookService.getAllBook().subscribe((books)=>{
		  this.books = books;
		  console.log(books);
	  });
  }
  
  deleteBook(book){
	  this.bookService.deleteBook(book.bookId).subscribe((data)=>{
		  this.books.splice(this.books.indexOf(book),1);
	  });
  }
  updateBook(book){
	  this.bookService.setter(book);
	  this.router.navigate(['edit-book']);
  }
  newBook() {
	 let book = new Book();
	 this.bookService.setter(book);
     this.router.navigate(['add-book']);
  }
  
}
