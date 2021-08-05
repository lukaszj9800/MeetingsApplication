import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Event } from 'src/app/models/event';
import { User } from 'src/app/models/user';
import { UserList } from 'src/app/models/userList';
import { CategoryService } from 'src/app/services/categoryservices/category.service';
import { EventService } from 'src/app/services/eventservices/event.service';

@Component({
  selector: 'app-event-update',
  templateUrl: './event-update.component.html',
  styleUrls: ['./event-update.component.css']
})
export class EventUpdateComponent implements OnInit {

  public categories:Category[];//Lista wszytskich kategorii która zostanie wykorzystana w formularzu
  public idEventToEdit:number;//pole do którego zostanie wczytane id eventu który chcemy edytować z pamięci sesji

  //pola wykorzystane w formularzu edycji wybranego eventu
  public name:string;
  public date:Date;
  public description:string;
  public id:number;
  public idOwner:number;

  //konstruktor zawierający serwisy obsługujące obiekty typu Event lub Category oraz Router który pozwala na nawigowanie między komponentami.
  constructor(private eventService: EventService, private router:Router, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.idEventToEdit = Number(sessionStorage.getItem('idEventToEdit'));//pobranie id eventu który chcemy edytować z pamięci sesji.
    this.getOneEvent();//wywołanie metody który pobierze z bazy danych obiekt typu Event z podanym przez nas id
    this.listCategories();//wywołanie metody pobierającej i zapisującej  wszystkie kategorie do tablicy obiektów typu Category.
    //Poniżej kod odpowiedzialny za odświeżenie strony
    if (!sessionStorage.getItem('foo')) { 
      sessionStorage.setItem('foo', 'no reload')
      location.reload() 
    } else {
      sessionStorage.removeItem('foo') 
    }
  }
  

  //metoda wkorzystująca eventService do pobrania jednego obiektu typu Event z bazy danych na podstawie id wybranego eventu.
  getOneEvent(){
    this.eventService.getOne(Number(sessionStorage.getItem('idEventToEdit'))).subscribe(
      data => {
       console.log(data)
       this.name = data.name;
       this.id = data.id;
       this.description = data.description;
       this.date = data.date;
       this.idOwner = data.idOwner;
       console.log(data.users)

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  


  //metoda pobierająca dane z formularza i zapisująca te dane w bazie danych.
  onUpdateEvent(editForm: NgForm){
    this.eventService.updateEvent(editForm.value).subscribe(
      (response: any) => {
        editForm.reset();
        this.router.navigate(['eventList'])
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        editForm.reset();
      }
    );
  }


  //metoda wczutująca wszystkie kategorie z bazy danych do tablicy obiektów typu Category. Wykorzystuje ona metodę z serwisu categoryService.
  listCategories() {
    this.categoryService.getCategories().subscribe(
      data =>{
        this.categories = data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  
}
