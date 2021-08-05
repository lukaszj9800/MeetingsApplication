import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/login/auth.service";
import { UserAuth } from "src/app/models/user-auth";
import { EventService } from "src/app/services/eventservices/event.service";
import { Event } from "../../models/event"


@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  public events: Event[];//lista obiektów typu event, które zesotaną wyświetlone.
  userAuth: UserAuth;//obiekt zawierający dane uwierzetelniające
  currentId: number = Number(sessionStorage.getItem('currentUserId'));//wczytanie id aktualnie zalogowanego użytkownika
  
  //konstruktor zawierający sewisy dla obiektów typu Event oraz UserAuth oraz router odpowiedzialny za nawigowanie pomiędzy komponentami.
  constructor(private eventService: EventService, private authService: AuthService, private router: Router) { }

  ngOnInit(){
    
    //Poniżej fragment kodu odpowiedzialny za odświeżenie komponentu.
    if (!sessionStorage.getItem('foo')) { 
      sessionStorage.setItem('foo', 'no reload')
      this.currentId;
      location.reload() 
    } else {
      sessionStorage.removeItem('foo') 
    }
    this.logged();// wywoałnie metody opdowiedzialnej za pobranie danych uwierzetelniających
    this.listEvents();// wywołanie metody odpowiedzialnej za pobranie listy wszystkich eventów.
    
  }


  //metoda odpowiedzialna za pobranie danych wierzetelniającyh.
  logged(){
    this.authService.getLogged(sessionStorage.getItem('username')).subscribe(
      data =>{
        this.userAuth = data;
        sessionStorage.setItem('currentUserRoles', this.userAuth.roles)
        var id = this.userAuth.currentUserId.toString();
        sessionStorage.setItem('currentUserId', id)
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda przekierowująca do komponentu odpowiedzialnego za wyświetlenie szczegółowych informacji na temat wybranego eventu.
  public openAboutEvent(id:number): void{
    sessionStorage.setItem('eventId', id.toString());
    this.router.navigate(['eventAbout']);
  }


  

  //metoda pobierająca listę eventów, która zostanie wyświetlona.
  listEvents() {
    this.eventService.getEvents().subscribe(
      data =>{
        this.events = data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda przekierowująca do komponentu który wyświetla wszystkich użytkowników wpisanych do danego wydarzenia.
  public showMembers(id:number): void{
    sessionStorage.setItem('idEventToShowUserList', id.toString());
    this.router.navigate(['userList']);
  }
}
