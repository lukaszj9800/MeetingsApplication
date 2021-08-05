//serwis dla obiektów typu Event
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/login/auth.service';
import { EventInfo } from 'src/app/models/eventInfo';
import { User } from 'src/app/models/user';
import { UserList } from 'src/app/models/userList';
import { Event } from '../../models/event'

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiServerUrl = 'http://localhost:8082/api/event';

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getEvents(): Observable<Event[]>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<Event[]>(`${this.apiServerUrl}`, {headers, responseType: 'json'});
  }

  public deleteEvent(idEvent: number): Observable<any>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<any>(`${this.apiServerUrl}/deleteEvent/${idEvent}`, {headers, responseType: 'json'});
  }

  public addEvent(event: Event): Observable<Event>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<Event>(`${this.apiServerUrl}/addEvent`, event, {headers, responseType:'text' as 'json'});
  }

  public findEventByOwnerId (idOwner: number): Observable<EventInfo[]>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<EventInfo[]>(`${this.apiServerUrl}/findEventByOwnerId/${idOwner}`, {headers, responseType:'json'});
  }

  public getOneEvent(idEvent:number): Observable<EventInfo>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<EventInfo>(`${this.apiServerUrl}/getOneEvent/${idEvent}`, {headers, responseType: 'json'});
  }

  public joinToEvent(idUser:number, idEvent: number): Observable<Event>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<Event>(`http://localhost:8082/api/event/join/${idUser}/to/${idEvent}`, {headers, responseType:'json'});
  }

  
  public updateEvent(event: Event): Observable<Event>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<Event>(`http://localhost:8082/api/event/editEvent/`, event, {headers, responseType:'json'});
  }

  public getOne(id:number): Observable<Event>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<Event>(`${this.apiServerUrl}/getOne/${id}`, {headers, responseType: 'json'});
  }

  public getUserList(idEvent:number): Observable<UserList[]>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<UserList[]>(`${this.apiServerUrl}/getUserList/${idEvent}`, {headers, responseType: 'json'});
  }

  
}
