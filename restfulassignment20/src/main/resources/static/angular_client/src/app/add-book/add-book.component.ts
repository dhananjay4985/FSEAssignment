import { Component, OnInit } from '@angular/core';
import { Book } from '../shared/models/book';
import { BookService } from '../services/book.service';
import { ActivatedRoute,Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  
  private book: Book;
  
  constructor(private bookService: BookService,private router: Router,private route:ActivatedRoute) { }
 
  ngOnInit() {
	  this.book = this.bookService.getter();
  }
   processForm(){	
		  this.bookService.addBook(this.book)
		  .subscribe((data)=> {
			this.router.navigate(['list-book']);
		  });
  }
}