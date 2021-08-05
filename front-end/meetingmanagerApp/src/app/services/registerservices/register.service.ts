//Serwis dla obiektów typu Register
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Register } from 'src/app/models/register';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiServerUrl = 'http://localhost:8082/api/register';

  constructor(private http: HttpClient,) { }

  public addUser(register: Register): Observable<Register>{
    return this.http.post<Register>(`${this.apiServerUrl}`, register);
  }
}
