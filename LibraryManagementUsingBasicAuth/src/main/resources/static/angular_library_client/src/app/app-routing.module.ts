import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AddBookComponent } from './add-book/add-book.component';
import { ListBookComponent } from './list-book/list-book.component';
import { EditBookComponent } from './edit-book/edit-book.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { RegistrationComponent } from './registration/registration.component';


const routes: Routes =[ 
  {path: 'add-book',component:EditBookComponent}, 
  {path: 'list-book',component:ListBookComponent}, 
  {path: 'edit-book',component:EditBookComponent},
  {path: 'login-user',component:LoginUserComponent},
  {path: 'register',component:RegistrationComponent},
  {path: '',component:LoginUserComponent} 
  ];
  
@NgModule({
	imports:[RouterModule.forRoot(routes)],
	exports:[RouterModule]
})

export class AppRoutingModule{}
export const routingComponents = [LoginUserComponent,ListBookComponent,EditBookComponent,RegistrationComponent];
