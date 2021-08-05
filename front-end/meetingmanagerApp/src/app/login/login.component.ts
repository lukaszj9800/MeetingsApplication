
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //zmienne wykorzystane podczas logowania
  username: string;
  password: string;

  //konstruktor zawierający serwis dla obiektów UserAuth, obsugujący uwierzetelnianie; obiekt Router wykorzystywany do nawigowania pomiędzy komponentami.
  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit() {

    //fragment kodu który odświeża stronę.
    if (!sessionStorage.getItem('foo')) { 
      sessionStorage.setItem('foo', 'no reload') 
      location.reload() 
    } else {
      sessionStorage.removeItem('foo') 
    }
  }

  //metoda który pobiera dane uwierzetelniające od użytkownika a następnie przyznaje lub odmawia dostępu do aplikacji.
  doLogin(){
    let resp = this.authService.login(this.username, this.password);
    resp.subscribe(data=>{
      console.log(data)
      sessionStorage.setItem('check', 'yes');
      sessionStorage.setItem('username', this.username);
      sessionStorage.setItem('password', this.password);
      this.router.navigate(['eventList']);
     
    },
    (error: HttpErrorResponse) => {
      alert("Invalid username or password!");
    }
    );
  }
  

  //metoda która przekierowuje nas do komponentu rejestracji
  public openRegister(): void{
    this.router.navigate(['register']);
  }

}
