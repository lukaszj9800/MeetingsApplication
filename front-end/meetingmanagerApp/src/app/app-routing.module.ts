//Jest to moduł zawierający tzw routingi do danych komponentów
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { EventListComponent } from "src/app/event/event-list/event-list.component";
import { EventAboutComponent } from "./event/event-about/event-about.component";
import { EventAddComponent } from "./event/event-add/event-add.component";
import { EventUpdateComponent } from "./event/event-update/event-update.component";
import { UserEventComponent } from "./event/user-event/user-event.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { UserListComponent } from "./user/user-list/user-list.component";
import { UserUpdateComponent } from "./user/user-update/user-update.component";

const routes: Routes = [
    {
        path:'eventList',//ścieżka pod jaką znajdziemy komponent
        component:EventListComponent//komponent który znajdziemy pod powyższą ścieżką
    },
    {
        path:'userList',
        component:UserListComponent
    },
    {
        path:'eventUpdate',
        component:EventUpdateComponent
    },
    {
        path:'eventAbout',
        component:EventAboutComponent
    },
    {
        path:'userEvent',
        component: UserEventComponent
    },
    {
        path:'userUpdate',
        component: UserUpdateComponent,
    },
    {
        path:'login',
        component: LoginComponent
    },
    {
        path:'eventAdd',
        component: EventAddComponent
    },
    {
        path:'register',
        component: RegisterComponent
    },
    {
        path:'',
        redirectTo:'/login',
        pathMatch:'full'
    }

]

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes, {
        paramsInheritanceStrategy: 'always'
    })],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }