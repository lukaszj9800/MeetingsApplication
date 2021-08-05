package pl.jagiela.model;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//Klasa(główny model) reprezentująca eventy


//Poniższe adnotacje oznaczają stworzenie w bazie danych tabeli
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id;//id eventu (klucz podstawowy w tabeli event)


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;// obiekt typu User reprezentujący właściciela danego eventu

    @Column(name = "idOwner")
    private Long idOwner;//id właściciela eventu

    @Column(name = "event_name")
    private String name;//nazwa wydarzenia

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;//obiekt typu Category reprezentujący kategorię danego eventu

    @Column(name ="idCategory")
    private Long  idCategory;// id kategorii eventu

    @Column(name = "places")
    private int places;// liczba miejsc

    @Column(name = "available_places")
    private int availablePlaces; //liczba dostępnych miejsc

    @ManyToMany(fetch = FetchType.LAZY)//doładowywanie danych tylko gdy o nie poprosimy
    @JoinTable(name = "event_user",
            joinColumns = {@JoinColumn(name="event_id", referencedColumnName="id_event")},
            inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id_user")})
    private List<User> users = new ArrayList<>();//lista użytkowników zapisanych do wydarzenia połączona relacją ManyToMany z tablą User

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;//data wydarzenia

    @Column(name = "description")
    private String description;//opis wydarzenia

    @Column(name = "totalCost")
    private double totalCost;//koszt całkowity

    public Event(){}//konstruktor bezparametrowy

    //konstruktor
    public Event(User owner, Long idOwner, String name, Category category, Long idCategory, int places, int availablePlaces, List<User> users, LocalDate date, String description, double totalCost) {
        this.owner = owner;
        this.idOwner = idOwner;
        this.name = name;
        this.category = category;
        this.idCategory = idCategory;
        this.places = places;
        this.availablePlaces = availablePlaces;
        this.users = users;
        this.date = date;
        this.description = description;
        this.totalCost = totalCost;
    }

    //gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
