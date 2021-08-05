package pl.jagiela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.model.Register;
import pl.jagiela.service.RegisterService;
//kontroler obsługujący rejestrację nowego użytkownika
@CrossOrigin
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private RegisterService service;

    //konstruktor wstrzykujący instancję typu RegisterService do wykonywania działań na bazie danych.
    @Autowired
    public RegisterController(RegisterService service) {
        this.service = service;
    }

    //endpoint obsługujący dodawanie nowego użytkownika do bazy danych(klasy UserAuth oraz User)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody Register register){
        service.addUser(register);
    }
}
