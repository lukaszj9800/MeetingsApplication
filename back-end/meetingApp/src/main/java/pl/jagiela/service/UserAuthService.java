package pl.jagiela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jagiela.dto.UserAuthDto;
import pl.jagiela.mapper.UserAuthDtoMapper;
import pl.jagiela.model.UserAuth;
import pl.jagiela.repository.UserAuthRepository;
import pl.jagiela.security.PasswordConfig;
import java.util.List;
import java.util.stream.Collectors;

//Serwis ten obsługuje operacje związane z klasą zawierającą dane uwierzytelniające
@Service
public class UserAuthService {

    private UserAuthRepository repository;
    private PasswordConfig passwordConfig;

    //wstrzyknięcie poprzez konstruktor repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu UserAuth, oraz klasy PasswordConfig która zawiera konfigurację enkodera hasła
    @Autowired
    public UserAuthService(UserAuthRepository repository, PasswordConfig passwordConfig) {
        this.repository = repository;
        this.passwordConfig = passwordConfig;
    }

    ///metoda która dodaje nowe dane uwierzytelniające do bazy danych
    public Long addUserAuth(UserAuth userAuth){
        userAuth.setPassword( passwordConfig.passwordEncoder().encode(userAuth.getPassword()));
        if(userAuth.getUserName() == "admin"){
            userAuth.setRoles("user:read, user:write");
        }else{
            userAuth.setRoles("user:read");
        }

        userAuth.setActive(true);
        UserAuth userAuth1 = repository.save(userAuth);
        return userAuth1.getId();
    }

    //wypisanie listy wszystkich obiektów typu UserAuth zawierających informacje na temat danych uwierzytelniających
    public List<UserAuth> getAuth(){
        return repository.findAll();
    }


    //Pobranie danych uwierzytelniających aktualnie zalogowanego użytkownika
    public UserAuthDto getLogged(String userName){
        List<UserAuthDto> user = repository.findUserAuthByUserName(userName)
                .stream()
                .map(UserAuthDtoMapper::mapToDto)
                .collect(Collectors.toList());
        UserAuthDto logged = user.get(0);
        return logged;
    }

    //edycja danych uwierzytelniającyh
    public void editUserAuth(UserAuth userAuth){
        UserAuth user = repository.getOne(userAuth.getId());
        userAuth.setPassword( passwordConfig.passwordEncoder().encode(userAuth.getPassword()));
        userAuth.setRoles(userAuth.getRoles());
        userAuth.setActive(true);
        repository.save(userAuth);
    }
}
