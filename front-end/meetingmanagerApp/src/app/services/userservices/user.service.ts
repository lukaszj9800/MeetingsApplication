//Serwis dla obiektów typu User
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = 'http://localhost:8082/api/user';

  constructor(private http: HttpClient) { }

  public addUser(auth: User): Observable<User>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<User>(`${this.apiServerUrl}`, auth, {headers, responseType:'text' as 'json'});
  }

  public updateUser(user: User): Observable<User>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<User>('http://localhost:8082/api/user/edit', user, {headers, responseType:'json'});
  }

  public findUserById(id: number): Observable<User>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<User>(`${this.apiServerUrl}/findId/${id}`, {headers, responseType:'json'});
  }
}
