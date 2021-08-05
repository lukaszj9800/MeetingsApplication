//Główny komponent aplikacji
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'meetingmanagerApp';
  check: string = sessionStorage.getItem('check');
}
