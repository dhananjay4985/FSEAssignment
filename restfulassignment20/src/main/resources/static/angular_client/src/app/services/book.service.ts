import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Book } from '../shared/models/book';
import { Observable} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) {  }
  
  httpOptions = {
	  headers: new HttpHeaders({
		  'Content-Type':'application/json'
	  })
  };
  private book = new Book();
  
  baseURL: string  = "http://localhost:8080/library";

	getAllBook():Observable<Book[]>{
		return this.http.get<Book[]>(this.baseURL+'/allbooks',this.httpOptions);
	}
	
	getBookById(bookId: number){
		return this.http.get<Book>(this.baseURL+'/allbooks/'+bookId,this.httpOptions);
	}
	
	addBook(book: Book):Observable<Book>{
		console.log(this.baseURL+'/allbooks/',book,this.httpOptions)
		return this.http.post<Book>(this.baseURL+'/allbooks/',book,this.httpOptions);
	} 
	updateBook(book: Book):Observable<Book>{
		console.log(this.baseURL+'/allbooks/'+book.bookId,book,this.httpOptions);
		return this.http.put<Book>(this.baseURL+'/allbooks/'+book.bookId,book,this.httpOptions)
	}
	deleteBook(bookId: number){
		return this.http.delete(this.baseURL+'/allbooks/'+bookId,this.httpOptions);
	}
	setter(book:Book){
		this.book = book;
	}
	getter(){
		return this.book;
	}
	
//	private errorHandler(operation = 'operation',result?: Book){
		//return Observable.throw(error||"server error");
	//}
 }