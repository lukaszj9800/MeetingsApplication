//Klasa reprezentująca obiekty typu User. Zawiera ona informacje na temat użytkowników.

import { UserAuth } from "./user-auth";

export class User{
    id: number;
    firstname: string;
    lastname: string;
    gender: string;
    imgUrl: string;
    userAuth: UserAuth;
    email:string;
    phone:string;
}