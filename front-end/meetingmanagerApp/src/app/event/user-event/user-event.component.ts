import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from "../../models/event"
import { EventService } from 'src/app/services/eventservices/event.service';
import { map } from 'rxjs/operators'
import { EventInfo } from 'src/app/models/eventInfo';
import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-user-event',
  templateUrl: './user-event.component.html',
  styleUrls: ['./user-event.component.css']
})
export class UserEventComponent implements OnInit {
 
  public events: EventInfo[];// tablica obiektów typu EventInfo których właścicielem jest aktualnie zalogowany użytkownik
  currentUserId: number = Number(sessionStorage.getItem('currentUserId'))//pobranie i przypisanie id aktualnie zalogowanego użytkownika

  //konstruktor zawierający serwis dla obiektów typu Event oraz Router który umożliwia nawigowania pomiędzy komponentami.
  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit(): void {
    if(this.listEvents() == null){
      sessionStorage.setItem('event', 'no');
    }
    this.listEvents();//wywoałnie metody pobierającej listę eventów których właścicielem jest aktualnie zalogowany użytkownik
    
  }


  // metoda która przenosi nas do komponentu wyświetlającego szczegóły na temat danego wydarzenia
  public openAboutEvent(id:number): void{
    sessionStorage.setItem('eventId', id.toString());
    this.router.navigate(['eventAbout']);
  }

    

  // metoda pobierająca listę eventów należacych do aktualnie zalogowanego użytkownika.
  listEvents(){
    console.log(this.currentUserId)
    this.eventService.findEventByOwnerId(this.currentUserId).subscribe(
      data => {
       this.events = data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda przekierowująca do komponentu w którym znajdują się wszyscy użytkownicy zapisani do danego eventu
  public showMembers(id:number): void{
    sessionStorage.setItem('idEventToShowUserList', id.toString());
    this.router.navigate(['userList']);
  }

  //metoda która usuwa wydarzenie o danym id a następnie przekierowuje do komponentu który wyświetla wszystkie wydarzenia
  public onDeleteEvent(id: number): void {
    this.eventService.deleteEvent(id).subscribe(
      (response: void) => {
        alert("Delete an event!");
        this.router.navigate(['eventList']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda przekierowująca do komponentu w którym znajduje się formularz do edycji danego eventu.
  public onUpdateEventOpen(idEvent:number): void{
    sessionStorage.setItem('idEventToEdit', idEvent.toString())
    this.router.navigate(['eventUpdate']);
  }


}
