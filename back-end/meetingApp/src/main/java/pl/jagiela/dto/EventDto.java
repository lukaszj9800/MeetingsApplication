package pl.jagiela.dto;
// obiekt dto zawierający tylko wybrane informacje na temat eventu które zostały pobrane z bazy danych

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EventDto {

    private Long id;
    private Long idOwner;
    private String name;
    private Long idCategory;
    private int places;
    private int availablePlaces;
    private List<UserListDto> users;
    private LocalDate date;
    private String description;


    //gettery i settery dla powyższych pól
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public List<UserListDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserListDto> users) {
        this.users = users;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;
        EventDto eventDto = (EventDto) o;
        return getPlaces() == eventDto.getPlaces() && getAvailablePlaces() == eventDto.getAvailablePlaces() && Objects.equals(getId(), eventDto.getId()) && Objects.equals(getIdOwner(), eventDto.getIdOwner()) && Objects.equals(getName(), eventDto.getName()) && Objects.equals(getIdCategory(), eventDto.getIdCategory()) && Objects.equals(getUsers(), eventDto.getUsers()) && Objects.equals(getDate(), eventDto.getDate()) && Objects.equals(getDescription(), eventDto.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdOwner(), getName(), getIdCategory(), getPlaces(), getAvailablePlaces(), getUsers(), getDate(), getDescription());
    }
}
