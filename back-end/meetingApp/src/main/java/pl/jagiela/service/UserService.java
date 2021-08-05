package pl.jagiela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jagiela.dto.UserDto;
import pl.jagiela.dto.UserListDto;
import pl.jagiela.mapper.UserDtoMapper;
import pl.jagiela.mapper.UserListDtoMapper;
import pl.jagiela.model.User;
import pl.jagiela.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

//Serwis ten obsługuje operacje związane z klasą zawierającą informacje na temat użytkownika
@Service
public class UserService {

    private UserRepository userRepository;

    //wstrzyknięcie poprzez konstruktor repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu User
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //pobranie listy wszystkich użytkowników z bazy danych
    public List<UserDto> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //Pobranie jednego użytkownika w reprezentacji klasy UserListDto z bazy danych na podstawie podanego id
    public UserListDto getUser(Long id){
        UserListDto userListDto = userRepository.findById(id).map(UserListDtoMapper::mapToDto).orElseThrow(() ->new IllegalArgumentException("The object does not exist"));
        return userListDto;
    }

    //dodanie nowego użytkownika do bazy dancyh
    public void addUser(User user){
        if(user.getGender().equals("mężczyzna")){
            user.setImgUrl("https://bootdey.com/img/Content/avatar/avatar1.png");
        }else if(user.getGender().equals("kobieta")){
            user.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar8.png");
        }
        userRepository.save(user);
    }

    //usunięcie użytkownika z bazy danych
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //edycja danych o podanym użytkowniku które zostały zapisane w bazie danych
    public void editUser(User user){
        User userToEdit = userRepository.getOne(user.getId());
        userToEdit.setFirstname(user.getFirstname());
        userToEdit.setLastname(user.getLastname());
        userRepository.save(userToEdit);
    }

    //pobranie użytkownika z bazy danych korzystając z podanego imienia
    public UserDto findUser(String firstname){
        return UserDtoMapper.mapToDto(userRepository.findByFirstnameContainingIgnoreCase(firstname));
    }

    //Pobranie jednego użytkownika w reprezentacji klasy UserDto z bazy danych na podstawie podanego id
    public UserDto findUserById(Long id){
        return UserDtoMapper.mapToDto(userRepository.getOne(id));
    }



}
