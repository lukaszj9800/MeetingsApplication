//Główny moduł aplikacji w którym wczytane są wszystkie komponenty oraz moduły użyte w aplikacji.

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EventListComponent } from './event/event-list/event-list.component';
import { EventAddComponent } from './event/event-add/event-add.component';
import { EventUpdateComponent } from './event/event-update/event-update.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { NavComponent } from './nav/nav.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './register/register.component';
import { UserEventComponent } from './event/user-event/user-event.component';
import { EventAboutComponent } from './event/event-about/event-about.component';
import { GoogleMapsModule } from '@angular/google-maps'
import { ReactiveFormsModule } from '@angular/forms'

@NgModule({
  declarations: [
    AppComponent,
    EventListComponent,
    EventAddComponent,
    EventUpdateComponent,
    UserListComponent,
    UserUpdateComponent,
    LoginComponent,
    NavComponent,
    RegisterComponent,
    UserEventComponent,
    EventAboutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    GoogleMapsModule,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
