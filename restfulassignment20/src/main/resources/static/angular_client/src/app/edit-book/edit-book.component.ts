import { Component, OnInit } from '@angular/core';
import { Book } from '../shared/models/book';
import { BookService } from '../services/book.service';
import { ActivatedRoute ,Router } from '@angular/router';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  private book: Book;
  
  constructor(private bookService: BookService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit() {
	   this.book = this.bookService.getter();
  }

  processForm(){	  
		  this.bookService.updateBook(this.book)
		  .subscribe((data)=> {			  
			  this.router.navigate(['list-book']);
		  });
  }
}
