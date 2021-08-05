import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from '../models/register';
import { RegisterService } from '../services/registerservices/register.service';
import { UserService } from '../services/userservices/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formGroup: FormGroup;// obiekt odpowiedzialny za walidację formularza rejestracji
  registerObject:Register = new Register();// obiekt odpowiedzialny za dodanie do bazy danych nowego użytkownika wraz z nowymi uwierzetelnieniami
  genderNames: string[] = ["mężczyzna", "kobieta"];

  // konstruktor zawierający obiekt typu FormBuldier związany z validacją formularza rejstracji, serwis dla obiektów typu Register oraz Router do nawigacji między komponentami
  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit(): void {
    sessionStorage.setItem('foo', 'no reload')
    //formularz walidacyjny rejestracji
    this.formGroup = this.formBuilder.group({
      registerForm: this.formBuilder.group({
        username: new FormControl('', Validators.required),
        password: new FormControl('', [Validators.required, Validators.minLength(8)]),
        firstname: new FormControl('', [Validators.required]),
        lastname: new FormControl('', [Validators.required]),
        phone: new FormControl('', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern("^[0-9]*$")]),
        email: new FormControl('', [Validators.required, Validators.email]),
        gender: new FormControl('', Validators.required),
      })
      
    })
  }

  //metoda dodająca nowego użytkownika
  public onAddAuth(register: Register): void {
    this.registerService.addUser(register).subscribe(
      (response: any) => {
        this.router.navigate(['login'])
        this.username.value
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        this.router.navigate(['login']);
      }
    );
  }

  //metoda odbierjąca z formularza dane i wysyłająca je do metody onAddAuth
  public onSubmit(){
    if(this.formGroup.invalid){
      this.formGroup.markAllAsTouched();
      return;
    }
    this.registerObject.firstname = this.firstname.value;
    this.registerObject.lastname = this.lastname.value;
    this.registerObject.userName = this.username.value;
    this.registerObject.password = this.password.value;
    this.registerObject.phone = this.phone.value;
    this.registerObject.email = this.email.value;
    this.registerObject.gender = this.gender.value;
    this.onAddAuth(this.registerObject);
  }

  //gettery dla pól z formularza rejestracji.
  get username(){
    return this.formGroup.get("registerForm.username");
  }
  get password(){
    return this.formGroup.get("registerForm.password");
  }
  get firstname(){
    return this.formGroup.get("registerForm.firstname");
  }
  get lastname(){
    return this.formGroup.get("registerForm.lastname");
  }
  get phone(){
    return this.formGroup.get("registerForm.phone");
  }
  get email(){
    return this.formGroup.get("registerForm.email");
  }
  get gender(){
    return this.formGroup.get("registerForm.gender");
  }

}
