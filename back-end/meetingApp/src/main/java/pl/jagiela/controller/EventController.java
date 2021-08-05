package pl.jagiela.controller;
//kontroler obsługujący operacje dotyczące obiektów typu Event
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.dto.EventDto;
import pl.jagiela.dto.EventInfoDto;
import pl.jagiela.dto.UserListDto;
import pl.jagiela.model.Event;
import pl.jagiela.service.EventService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/event")
public class EventController {
    private EventService eventService;

    //konstruktor wstrzykujący instancję typu EventService do wykonywania działań na bazie danych.
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //endpoint usuwający dany event z bazy danych po id
    @PostMapping(path = "/deleteEvent/{idEvent}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEvent(@PathVariable Long idEvent){
        eventService.deleteEvent(idEvent);
    }

    //endpoint pobierający z bazy dannych wszystkie eventy i przekształca obiekty typu Event na EventInfoDto w celu pobrania wybranych pól
    @GetMapping("")
    public List<EventInfoDto> getEvents(){
        return eventService.getEvents();
    }

    //endpoint pobierjący jeden wybrany event(do wyświetlenia go) po id i zwraca obiekt typu EventInfoDto
    @GetMapping("/getOneEvent/{idEvent}")
    public EventInfoDto getEvent(@PathVariable Long idEvent){
        return eventService.getEvent(idEvent);
    }

    //endpoint dodający Event do bazy dancyh.
    @PostMapping(path = "/addEvent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    //endpoint do edycji wybranego eventu.
    @PostMapping("/editEvent")
    public void editEvent(@RequestBody Event event){
        eventService.editEvent(event);
    }

    //endpoint obsługujący dodanie użytkownika do danego eventu
    @PostMapping("/join/{idUser}/to/{idEvent}")
    public void joinToEvent(@PathVariable Long idUser, @PathVariable Long idEvent){
        eventService.joinToEvent(idUser, idEvent);
    }

    //endpoint zwracający listę eventów którego właścicielem jest użytkownik o podanym id
    @GetMapping("/findEventByOwnerId/{idOwner}")
    public List<EventInfoDto> findEventByOwnerId(@PathVariable Long idOwner){
        return eventService.findByOwnerId(idOwner);
    }

    //endpoint zwracający jeden obiekt typu EventDto(do edycji)
    @GetMapping("/getOne/{id}")
    public EventDto getOne(@PathVariable Long id){
        return eventService.getOne(id);
    }

    //endpoint zwracający listę użytkowników zapisanych do eventu o podanym id
    @GetMapping("/getUserList/{idEvent}")
    public List<UserListDto> getUserList(@PathVariable Long idEvent){
        return eventService.getUserList(idEvent);
    }


}
