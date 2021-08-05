package pl.jagiela.mapper;
//serwis konwertujący obiekty typu Event na EventDto
import org.springframework.stereotype.Service;
import pl.jagiela.dto.EventDto;
import pl.jagiela.model.Event;
import pl.jagiela.model.User;
import pl.jagiela.repository.EventRepository;

import java.util.stream.Collectors;

@Service
public class EventDtoMapper {

    //metoda konwertująca obiekty typu Event które zostaną do niej przekazane na EventDto
    public static EventDto mapToDto(Event event){
        EventDto eventDto = new EventDto();// obiekt typu EventDto który zostanie zwrócony

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        eventDto.setId(event.getId());
        eventDto.setIdCategory(event.getIdCategory());
        eventDto.setIdOwner(event.getIdOwner());
        eventDto.setDate(event.getDate());
        eventDto.setDescription(event.getDescription());
        eventDto.setName(event.getName());
        eventDto.setPlaces(event.getPlaces());
        eventDto.setAvailablePlaces(event.getAvailablePlaces());
        eventDto.setUsers(event.getUsers().stream().map(UserListDtoMapper::mapToDto).collect(Collectors.toList()));
        return eventDto;
    }
}
