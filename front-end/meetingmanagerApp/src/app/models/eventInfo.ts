//Klasa reprezentująca obiekty typu EventInfo, zawierający tylko wbrane pola z ustalonymi odgórnie nazwami(w back-end reprezentuje obiekt EventInfoDto)

import { UserList } from "./userList";

export class EventInfo{
    id: number;
    name: string;
    idOwner: number;
    owner: string;
    category: string;
    places: number;
    availablePlaces: number;
    day: number;
    month: string;
    year:number;
    users:UserList[];
    description: string;
    categoryImgUrl: string;
    totalCost:number;

    
}