import { Component, OnInit } from '@angular/core';
import { Book } from '../shared/models/book';
import { BookService } from '../services/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  
  private book: Book;
  
  constructor(private bookService: BookService,private router: Router) { }
 
  
  ngOnInit() {
	  this.book = this.bookService.getter();
  }
   processForm(){
		  this.bookService.addBook(this.book).subscribe((book)=>{
			  console.log("hiiii");
		  });
	  }
}