// Serwis w którym występuje połączenie między back-end a front-end. Implemetuje on endpointy zawarte w kontrolerze z back-endu.

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserAuth } from '../models/user-auth';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username: string;
  password: string;

  private apiServerUrl = 'http://localhost:8082/api/login';
  constructor(private http: HttpClient) { }

  public login(username: string, password: string){
    this.username = username;
    this.password = password;
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(username+":"+password)})
    return this.http.get("http://localhost:8082/api/login",{headers, responseType:'text' as 'json'})
  }

  public getLogged(userName: string): Observable<UserAuth>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<UserAuth>(`http://localhost:8082/api/auth/logged/${userName}`, {headers, responseType: 'json'});
  }
  public updateAuth(userAuth: UserAuth): Observable<UserAuth>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.post<UserAuth>('http://localhost:8082/api/auth/edit', userAuth, {headers, responseType:'json'});
  }

}
