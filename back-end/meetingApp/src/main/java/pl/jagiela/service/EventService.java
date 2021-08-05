package pl.jagiela.service;
//Serwis zawierający wszystkie metody które mogą zostać wykonane w kontrolerze i przy użyciu serwisu oraz repozytorium pobrane, zapisane, usunięte lub zaktualizowane w bazie danych
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jagiela.dto.EventDto;
import pl.jagiela.dto.EventInfoDto;
import pl.jagiela.dto.UserListDto;
import pl.jagiela.mapper.EventDtoMapper;
import pl.jagiela.mapper.EventInfoDtoMapper;
import pl.jagiela.mapper.UserListDtoMapper;
import pl.jagiela.model.Event;
import pl.jagiela.model.User;
import pl.jagiela.repository.CategoryRepository;
import pl.jagiela.repository.EventRepository;
import pl.jagiela.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;


//Serwis ten obsługuje operacje związane z eventami
@Service
public class EventService {

    private EventRepository eventRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    //wstrzyknięcie poprzez konstruktor repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu User, repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu Event oraz repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu Category
    @Autowired
    public EventService(UserRepository userRepository, EventRepository eventRepository, CategoryRepository categoryRepository ) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    //pobranie listy wszystkich eventów przekształconych z obiektu typu Event na EventInfoDto w celu pobrania tylko potrzebnych danych
    public List<EventInfoDto> getEvents(){
        return eventRepository.findAll()
                .stream()
                .map(EventInfoDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //pobranie jednego eventu po podanym id przekształconego w obiekt typu EventInfoDto w celu pobrania tylko potrzebnych danych
    public EventInfoDto getEvent(Long id){
        return eventRepository.findById(id).map(EventInfoDtoMapper::mapToDto).orElseThrow(() ->new IllegalArgumentException("The object does not exist"));
    }

    //pobranie listy eventów których właścicielem jest użytkownik o podanym id. W liście znajdują się obiekty przekształcone z typu Event na EventInfoDto w celu pobrania tylko potrzebnych dancyh
    public List<EventInfoDto> findByOwnerId(Long id){
        return eventRepository.findByOwnerId(id)
                .stream()
                .map(EventInfoDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //dodawanie nowego eventu oraz ustawiania właściciela oraz kategorię eventu zgodnie z wartościami pól(idOwner oraz idCategory)
    public void addEvent(Event event){
        event.setOwner(userRepository.getOne(event.getIdOwner()));
        event.setCategory(categoryRepository.getOne(event.getIdCategory()));
        eventRepository.save(event);
    }

    //usuwanie eventu z danym id
    public void deleteEvent(Long id){
        Event event = eventRepository.getOne(id);
        eventRepository.delete(event);
    }

    //edytowanie wybranego eventu
    public void editEvent(Event event){
        Event eventToEdit = eventRepository.getOne(event.getId());
        eventToEdit.setOwner(userRepository.getOne(event.getIdOwner()));
        eventToEdit.setCategory(categoryRepository.getOne(event.getIdCategory()));
        eventToEdit.setName(event.getName());
        eventToEdit.setDate(event.getDate());
        eventToEdit.setDescription(event.getDescription());
        eventRepository.save(eventToEdit);
    }



    //dołączenie do podanego eventu
    public void joinToEvent(Long idUser, Long idEvent){
        User user = userRepository.getOne(idUser);//pobranie z bazy danych użytkownika o podanym id i przypisanie go do obiektu
        Event event = eventRepository.getOne(idEvent);//pobranie z bazy danych eventu o podanym id i przypisanie go do obiektu
        try {
            if (!(event.getUsers().contains(user))) {
                // przypisanie do listy użytkowników użytkownika który wyżej został pobrany a następnie przypisanie listy do eventu który został pobrany wyżej
                int availablePlacesAfterJoin = event.getAvailablePlaces() - 1;
                List<User> users = event.getUsers();
                users.add(user);
                event.setUsers(users);
                event.setAvailablePlaces(availablePlacesAfterJoin);
                eventRepository.save(event);
            }else{
                throw new Exception("You have already joined the event!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //pobranie jednego eventu z danym id
    public EventDto getOne(Long id){
        return eventRepository.findById(id).map(EventDtoMapper::mapToDto).orElseThrow(() ->new IllegalArgumentException("The object does not exist"));
    }

    //pobranie listy użtykowników przypisanych do eventu o danym id
    public List<UserListDto> getUserList(Long idEvent){
        Event event = eventRepository.getOne(idEvent);
        return event.getUsers().stream().map(UserListDtoMapper::mapToDto).collect(Collectors.toList());
    }



}
