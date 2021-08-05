import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/userservices/user.service';
import { AuthService } from 'src/app/login/auth.service';
import { UserAuth } from 'src/app/models/user-auth';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html', 
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  public currentUser: User;// klasa która przechowuje obiekt aktualnie zalogowanego użytkownika
  public userAuth: UserAuth;// klasa przechowująca dane logowania aktualnie zalogowanego użytkownika
  //poniżej pola formularza wykorzystywane do edycji ustawień i danych aktualnie zalogowanego użytkownika
  public firstname: string;
  public lastname: string;
  public userName: string;
  public password: string;
  public email: string;
  public phone: string;
  public imgUrl:string;


  //konstruktor zawierający serwisy UserService oraz AuthService obsługujące operacje na obiektach typu User i UserAuth oraz Router odpowiedzialny za nawigację pomiędzy komponentami.
  constructor(private userService: UserService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    //Kod odpowiedzialny za odświeżenie strony
    if (!sessionStorage.getItem('foo')) { 
      sessionStorage.setItem('foo', 'no reload')
      location.reload() 
    } else {
      sessionStorage.removeItem('foo') 
    }
    this.getCurrentUser();//wywołanie metody pobierjącej aktualnie zalogowanego użytkownika
    this.logged();//wywołanie metody pobierającej dane logowania aktualnie zalogowanego użytkownika
  }

  //metoda pobierjąca aktualnie zalogowanego użytkownika
  getCurrentUser(){
    this.userService.findUserById(Number(sessionStorage.getItem('currentUserId'))).subscribe(
      data=>{
        this.currentUser = data;
        this.firstname = this.currentUser.firstname;
        this.lastname = this.currentUser.lastname;
        this.email = this.currentUser.email;
        this.phone = this.currentUser.phone;
        this.imgUrl = this.currentUser.imgUrl;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //metoda pobierająca dane logowania aktualnie zalogowanego użytkownika
  logged(){
    this.authService.getLogged(sessionStorage.getItem('username')).subscribe(
      data =>{
        this.userAuth = data;
        this.userName = this.userAuth.userName;
        this.password = sessionStorage.getItem('password');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

 
  //metoda odpowiadająca za pobranie danych na temat użytkownika z formularza i zapisanie ich do bazy danych
  onUpdate(editForm: NgForm){
    editForm.controls['id'].setValue(this.currentUser.id);
    if(editForm.controls['firstname'].value == null ||editForm.controls['lastname'].value == null ){
      editForm.controls['firstname'].setValue(this.currentUser.firstname);
      editForm.controls['lastname'].setValue(this.currentUser.lastname);
    }
    this.userService.updateUser(editForm.value).subscribe(
      (response: any) => {
        editForm.reset();
        this.router.navigate(['eventList'])
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        editForm.reset();
      }
    );
  }

  //metoda odpowiadająca za pobranie danych logowania z formularza i zapisanie ich do bazy danych a następnie wylogowanie użytkownika.
  onUpdateAuth(editAuthForm: NgForm){
    editAuthForm.controls['id'].setValue(this.userAuth.id);
    editAuthForm.controls['roles'].setValue(this.userAuth.roles);
    if(editAuthForm.controls['userName'].value == null ||editAuthForm.controls['password'].value == null ){
      editAuthForm.controls['userName'].setValue(this.userAuth.userName);
      editAuthForm.controls['password'].setValue(this.userAuth.password);
    }
    this.authService.updateAuth(editAuthForm.value).subscribe(
      (response: any) => {
        alert("Successful changes! You will be logged out in a moment...")
        sessionStorage.setItem('check', 'no');
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('password');
        sessionStorage.removeItem('currentUserId');
        sessionStorage.removeItem('currentUserRoles');
        this.router.navigate(['login'])
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        editAuthForm.reset();
      }
    );
  }

}
