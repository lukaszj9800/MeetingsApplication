import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Event } from 'src/app/models/event';
import { CategoryService } from 'src/app/services/categoryservices/category.service';
import { EventService } from 'src/app/services/eventservices/event.service';


@Component({
  selector: 'app-event-add',
  templateUrl: './event-add.component.html',
  styleUrls: ['./event-add.component.css']
})
export class EventAddComponent implements OnInit {

  formGroup: FormGroup;// obiekt związany z walidacją formularza do dodawania nowego eventu
  addObject:Event = new Event();//obiekt typu event który zostanie dodany
  public categories: Category[];// lista kategori który zostanie wczytana i wykorzystana w formularzu.
  public currentId: number = Number(sessionStorage.getItem('currentUserId'));// pobranie id aktulanie zalogowanego użytkownika

  //Konstruktor zwierający obiekt FormBuilder, który odpowiedzialny będzie za walidację formularza, serwis dla obiektów typu Category, serwis dla obiektów typu Event ora Router wymagany do nawigacji pomiędzy komponentami.
  constructor(private formBuilder:FormBuilder, private categoryService: CategoryService, private eventService: EventService, private router: Router) { }

  ngOnInit(): void {
    this.listCategories();// wywołanie funckji która wczyta nam z bazy danych wszystkie kategorie do wyżej zaimplenetowanej listy obiektów typu Category.
    //poniżej fragment kodu który wykorzystuję do odświeżenia strony.
    if (!sessionStorage.getItem('foo')) { 
      sessionStorage.setItem('foo', 'no reload')
      this.currentId;
      location.reload() 
    } else {
      sessionStorage.removeItem('foo') 
    }

    // poniżej formularz walidacyjny dla formularza w którym dodamy nowy obiekt typu event
    this.formGroup = this.formBuilder.group({
      addEventForm: this.formBuilder.group({
        name: new FormControl('', Validators.required),
        idCategory: new FormControl('', [Validators.required]),
        places: new FormControl('', Validators.required),
        availablePlaces: new FormControl('', Validators.required),
        date: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required),
        totalCost: new FormControl('', Validators.required)
      })
    })
  }

  //metoda pobierająca i zapisująca do tablicy obiektów typu Category wszytskie kategorie
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

  //metoda dla przycisku w formularzu. Sprawdza ona czy wszystkie pola są wypełnione jeśli tak do dodaje do obiektu typu event wartości a następnie przy pomocy EventService zapisuje obiekt do bazy danych.
  public onSubmit(){
    if(this.formGroup.invalid){
      this.formGroup.markAllAsTouched();
      return;
    }
    this.addObject.name = this.name.value;
    this.addObject.idCategory = this.idCategory.value;
    this.addObject.places = this.places.value;
    this.addObject.availablePlaces = this.availablePlaces.value;
    this.addObject.date = this.date.value;
    this.addObject.description = this.description.value;
    this.addObject.idOwner = this.currentId;
    this.addObject.totalCost  = this.totalCost.value;
    this.onAddEvent(this.addObject);
  }


  //metoda do zapisywania obiektu typu Event.
  public onAddEvent(event:Event): void {
    this.eventService.addEvent(event).subscribe(
      (response: any) => {
        this.router.navigate(['eventList'])
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //gettery dla pól które występują w formularzu.
  get name(){
    return this.formGroup.get('addEventForm.name');
  }
  get idCategory(){
    return this.formGroup.get('addEventForm.idCategory');
  }
  get places(){
    return this.formGroup.get('addEventForm.places');
  }
  get availablePlaces(){
    return this.formGroup.get('addEventForm.availablePlaces');
  }
  get date(){
    return this.formGroup.get('addEventForm.date');
  }
  get description(){
    return this.formGroup.get('addEventForm.description');
  }
  get totalCost(){
    return this.formGroup.get('addEventForm.totalCost');
  }
  get addEventForm(){
    return this.formGroup.get('addEventForm');
  }

  

}
