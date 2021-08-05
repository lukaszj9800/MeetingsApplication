//serwis dla obiektów typu Category
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/login/auth.service';
import { Category } from 'src/app/models/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiServerUrl = 'http://localhost:8082/api/category';

  constructor(private http: HttpClient, private auth: AuthService) { }

  public getCategories(): Observable<Category[]>{
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(sessionStorage.getItem('username')+":"+sessionStorage.getItem('password'))})
    return this.http.get<Category[]>(`${this.apiServerUrl}`, {headers, responseType: 'json'});
  }
}
