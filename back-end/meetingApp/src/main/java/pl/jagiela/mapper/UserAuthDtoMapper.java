package pl.jagiela.mapper;
//serwis konwertujący obiekty typu UserAuth na UserAuthDto
import org.springframework.stereotype.Service;
import pl.jagiela.dto.UserAuthDto;
import pl.jagiela.model.UserAuth;

@Service
public class UserAuthDtoMapper {
    //metoda konwertująca obiekty typu UserAuth które zostaną do niej przekazane na UserAuthDto
    public static UserAuthDto mapToDto(UserAuth userAuth){
        UserAuthDto userAuthDto = new UserAuthDto();// obiekt typu UserAuthDto który zostanie zwrócony

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        userAuthDto.setId(userAuth.getId());
        userAuthDto.setUserName(userAuth.getUserName());
        userAuthDto.setPassword(userAuth.getPassword());
        userAuthDto.setCurrentUserId(userAuth.getUser().getId());
        userAuthDto.setRoles(userAuth.getRoles());
        return userAuthDto;
    }
}
