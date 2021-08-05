package pl.jagiela.mapper;
//serwis konwertujący obiekty typu User na UserDto
import org.springframework.stereotype.Service;
import pl.jagiela.dto.UserDto;
import pl.jagiela.model.User;

@Service
public class UserDtoMapper {

    //metoda konwertująca obiekty typu User które zostaną do niej przekazane na UserDto
    public static UserDto mapToDto(User user){
        UserDto userDto = new UserDto();// obiekt typu UserDto który zostanie zwrócony

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setGender(user.getGender());
        userDto.setImgUrl(user.getImgUrl());


        return userDto;
    }
}
