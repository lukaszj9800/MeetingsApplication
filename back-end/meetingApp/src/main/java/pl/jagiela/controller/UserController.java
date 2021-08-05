package pl.jagiela.controller;

//kontroler obsługujący operacje na klasie User, która jest odpowiedzialna za reprezentację danych użytkownika
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.dto.UserDto;
import pl.jagiela.dto.UserListDto;
import pl.jagiela.model.User;
import pl.jagiela.service.UserService;
import java.util.List;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    //konstruktor wstrzykujący instancję typu UserService do wykonywania działań na bazie danych.
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //endpoint pobierający listę wszystkich użytkowników  i przekształcający obiekty typu User na UserDto w celu pobrania tylko wybranych informacji na temat użtykownika
    @GetMapping("")
    public List<UserDto> getUser(){
        return userService.getUsers();
    }

    //endpoint do dodawania nowego użytkownika
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    //endpoint do usuwania użytkowników
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    //endpoint do edytowania informacji na temat danego użytkownika
    @PostMapping("/edit")
    public void editUser(@RequestBody User user){
        userService.editUser(user);
    }

    //endpoint do znajdywania użytkownika po jego id.
    @GetMapping("findId/{id}")
    public UserDto findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    //endpoint do pobierania  użytkownika który przekonwertowanego na obiekt UserListDto który jest wykorzystywany przy wyświetlaniu listy użytkowników.
    @GetMapping("/getOneUser/{idUser}")
    public UserListDto getEvent(@PathVariable Long idUser) {
        return userService.getUser(idUser);
    }
}
