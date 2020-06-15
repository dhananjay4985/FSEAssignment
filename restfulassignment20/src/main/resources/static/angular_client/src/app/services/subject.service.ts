import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Subject } from '../shared/models/subject';


@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  constructor(private http: HttpClient) { }
  
  subjectURL: string ="http://localhost:8080/library";
	
	getAllSubject(){
			return this.http.get<Subject[]>(this.subjectURL);
	}
		
  
  }
