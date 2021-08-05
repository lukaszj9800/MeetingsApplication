//Klasa reprezentująca obiekty typu Event. Główna reprezentacja klasy Event przedstawiająca wszystkie pola.

import { Category } from "./category";
import { User } from "./user";

export class Event{
    id: number;
    name: string;
    idOwner: number;
    idCategory: number;
    owner: User;
    category: Category;
    places: number;
    availablePlaces: number;
    description: string;
    users:User[];
    date: Date;
    day: number;
    month: string;
    year: number;
    totalCost:number;
}