import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/app/models/event';
import { EventInfo } from 'src/app/models/eventInfo';
import { EventService } from 'src/app/services/eventservices/event.service';

@Component({
  selector: 'app-event-about',
  templateUrl: './event-about.component.html',
  styleUrls: ['./event-about.component.css']
})
export class EventAboutComponent implements OnInit {

  public idEvent:number = Number(sessionStorage.getItem('eventId'));//pobranie s pamięci sesji id eventu którego szczegóły chcemy wyświetlić
  public event:EventInfo;// utworzenie obiektu do którego wczytamy obiekt typu EventInfo reprezentujący wybrany event


  //Poniżej pola pobranego z bazy obiektu typu EventInfo
  public name:string;
  public places:number;
  public availablePlaces:number;
  public day: number;
  public month: string;
  public year:number;
  public description:string;
  public owner:string
  public category:string;
  public categoryImgUrl:string;
  public totalCost: number;
  
  public currentId:number;//pole do którego będzie wczytane id aktualnie zalogowanego użytkownika.
  

  constructor(private service:EventService, private router:Router) { }//konstruktor zawierający serwis dla operacji(do obiektów typu Event) pomiędzy back-endem a front-endem, a także obiekt router, który nawiguje nas pomiędzy komponentami.


  


  ngOnInit(): void {
    this.currentId = Number(sessionStorage.getItem('currentUserId'));// wczytanie id aktualnie zalogowanego użytkownika.
    this.getEvent();// wywowałanie funkcji getEvent która wczytuje dane wydarzenie do obiektu który został wyżej zaimplementowany
   
  }


  //Poniżej metoda odpowiadająca za dołączenie do wyświetlonego eventu. Metoda pobiera id aktualnie zalogowanego użytkownika oraz id wybranego eventu i odwołuje się do metody z back-endu która zostła zaimplentowana w EventService.
  joinToEvent(idUser:number, idEvent:number){
    this.service.joinToEvent(idUser, idEvent).subscribe( 
      (response: any) => {
        this.router.navigate(['eventList'])
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda której zadaniem  jest przekierowanie do komponentu który wyświetli listę użytkowników wpisanych do danego eventu.
  public showMembers(id:number): void{
    sessionStorage.setItem('idEventToShowUserList', id.toString()); //przesałanie id wybranego eventu do pamięci sesji
    this.router.navigate(['userList']);// przekierowanie do komponentu który wyświetli listę uczestników danego eventu.
  }

  // metoda pobierjąca jeden wybrany przez użytkownika event aby móc wyświetlić o nim szczegóły
  getEvent(){
    this.service.getOneEvent(Number(sessionStorage.getItem('eventId'))).subscribe(
      data =>{
        this.event = data;
        this.name = data.name;
        this.month = data.month;
        this.day = data.day;
        this.year = data.year;
        this.category = data.category;
        this.owner = data.owner;
        this.description = data.description;
        this.places = data.places;
        this.availablePlaces = data.availablePlaces;
        this.categoryImgUrl = data.categoryImgUrl;
        this.totalCost = data.totalCost;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
