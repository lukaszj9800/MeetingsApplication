import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserList } from 'src/app/models/userList';
import { EventService } from 'src/app/services/eventservices/event.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  
  public userList:UserList[];//Lista użytkowników do wyświetlenia
 
  //konstruktor zawierający serwis dla obiektów typu Event
  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.listUsers();//wywołanie metody listUsers która wczytuje wszystkich użytkowników zapisanych do danego eventu
    
  }

  //metoda która wczytuje wszystkich użytkowników zapisanych do danego eventu
  listUsers() {
    this.eventService.getUserList(Number(sessionStorage.getItem('idEventToShowUserList'))).subscribe(
      data =>{
        this.userList = data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda otwierająca nowe okno do wysłania maila do danego użytkownika 
  openMail(mail:string, name:string){
    window.open(`https://mail.google.com/mail/u/0/?view=cm&fs=1&to=${mail}&su=Question_about_event&body=Hi_${name}&tf=1`);
  }

  




}
