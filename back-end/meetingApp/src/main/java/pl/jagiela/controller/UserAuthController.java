package pl.jagiela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.dto.UserAuthDto;
import pl.jagiela.model.UserAuth;
import pl.jagiela.service.UserAuthService;
import java.util.List;

//kontroler obsługujący operacje na klasie UserAuth, która jest odpowiedzialna za reprezentację3 danych logowania użytkownika
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private UserAuthService userAuthService;

    //konstruktor wstrzykujący instancję typu UserAuthService do wykonywania działań na bazie danych.
    @Autowired
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    //endpoint dodający nowe dane uwierzytelniające do bazy danych
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long addUserAuth(@RequestBody UserAuth userAuth){
        return userAuthService.addUserAuth(userAuth);
    }

    //endpoint pobierający listy danych uwierzytelniających
    @GetMapping("")
    public List<UserAuth> getAuth(){
        List<UserAuth> auth= userAuthService.getAuth();
        return auth;
    }

    //endpoint pobierający jedną wybraną instancję danych uwierzytelniających po podanej nazwie użytkownika.
    @GetMapping("/logged/{userName}")
    public UserAuthDto getLogged(@PathVariable String userName){
        UserAuthDto logged = userAuthService.getLogged(userName);
        return logged;
    }

    //endpoint obsługujący edycję wybranych danych uwierzytelniających
    @PostMapping("/edit")
    public void editAuth(@RequestBody UserAuth userAuth){
        userAuthService.editUserAuth(userAuth);
    }
}
