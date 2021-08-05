package pl.jagiela.mapper;

import org.springframework.stereotype.Service;
import pl.jagiela.dto.EventInfoDto;
import pl.jagiela.model.Event;
import pl.jagiela.model.User;

//serwis konwertujący obiekty typu Event na EventInfoDto
import java.util.stream.Collectors;

@Service
public class EventInfoDtoMapper {

    //metoda konwertująca obiekty typu Event które zostaną do niej przekazane na EventInfoDto
    public static EventInfoDto mapToDto(Event event){
        EventInfoDto eventInfoDto = new EventInfoDto();//obiekt typu EventDto który zostanie zwrócony

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        eventInfoDto.setId(event.getId());
        eventInfoDto.setName(event.getName());
        eventInfoDto.setOwner(event.getOwner().getFirstname()+" "+event.getOwner().getLastname());
        eventInfoDto.setCategory(event.getCategory().getName());
        eventInfoDto.setAvailablePlaces(event.getAvailablePlaces());
        eventInfoDto.setPlaces(event.getPlaces());
        eventInfoDto.setUsers(event.getUsers().stream().map(UserListDtoMapper::mapToDto).collect(Collectors.toList()));
        eventInfoDto.setDay(event.getDate().getDayOfMonth());
        eventInfoDto.setMonth(event.getDate().getMonth().toString());
        eventInfoDto.setYear(event.getDate().getYear());
        eventInfoDto.setIdOwner(event.getIdOwner());
        eventInfoDto.setDescription(event.getDescription());
        eventInfoDto.setCategoryImgUrl(event.getCategory().getImgUrl());
        eventInfoDto.setTotalCost(event.getTotalCost());
        return eventInfoDto;
    }
}
