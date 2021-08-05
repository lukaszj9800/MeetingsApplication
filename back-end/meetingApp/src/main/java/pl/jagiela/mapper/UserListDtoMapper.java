package pl.jagiela.mapper;
//serwis konwertujący obiekty typu User na UserListDto
import org.springframework.stereotype.Service;
import pl.jagiela.dto.UserListDto;
import pl.jagiela.model.User;

@Service
public class UserListDtoMapper {

    //metoda konwertująca obiekty typu User które zostaną do niej przekazane na UserListDto
    public static UserListDto mapToDto(User user){
        UserListDto userListDto = new UserListDto();

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        userListDto.setId(user.getId());
        userListDto.setName(user.getFirstname()+" "+user.getLastname());
        userListDto.setImgUrl(user.getImgUrl());
        userListDto.setEmail(user.getEmail());
        userListDto.setPhone(user.getPhone());
        return userListDto;
    }
}
