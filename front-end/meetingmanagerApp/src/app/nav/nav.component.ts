import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  //zmienne wykorzystywane do sprawdzenia czy użytkownik jest zalogowany oraz czy posiada on eventy.
  check: string = sessionStorage.getItem('check');
  checkEvent:string = sessionStorage.getItem('event');

  //konstruktor zawierający obiekt typu Router do nawigowania pomiędzy komponentami.
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  //metoda która otwiera komponent odpowiedzialny za dodawanie nowych eventów
  public openAddEvent(): void{
    this.router.navigate(['eventAdd']);
  }

  //metoda która otwiera komponent odpowiedzialny za wyświetlenie eventów należących do danego użytkownika.
  public openUserEvent(): void{
    this.router.navigate(['userEvent']);
  }

  //metoda która otwiera komponent odpowiedzialny za wyświetlenie formularza do edycji ustawień użytkownika.
  public openUpdateUser(): void{
    this.router.navigate(['userUpdate']);
  }

  //metoda jest odpowiedzialna za wylogowanie się z aplikacji.
  doLogout(): void{
    alert("You will be logged out in a moment!")
    sessionStorage.setItem('check', 'no');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('password');
    sessionStorage.removeItem('currentUserId');
    sessionStorage.removeItem('currentUserRoles');
    this.router.navigate(['login'])
    
    
  }
}
