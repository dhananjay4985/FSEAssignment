import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { SubjectService } from './services/subject.service';
import { BookService } from './services/book.service';

import {AppRoutingModule,routingComponents} from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
	routingComponents
      ],
  imports: [	
    BrowserModule,
	HttpClientModule,
	FormsModule,
	AppRoutingModule
  ],
  providers: [SubjectService,BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
