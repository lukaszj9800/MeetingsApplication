//Klasa reprezentująca obiekt UserAuth. Zawiera ona dane uwierzetelniające.
export class UserAuth {
    id: number;
    userName: string;
    password: string;
    active: boolean;
    roles: string;
    currentUserId: number;   
}
