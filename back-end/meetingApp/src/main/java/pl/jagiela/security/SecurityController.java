package pl.jagiela.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.auth.ApplicationUser;
import pl.jagiela.auth.ApplicationUserService;
//klasa kontrolera zawierająca endpoint odpowiedzialny za logowanie w fron-end
@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class SecurityController {

    @Autowired
    private ApplicationUserService service;

    @GetMapping("")
    public String login(){
        return "logged in";
    }





}
