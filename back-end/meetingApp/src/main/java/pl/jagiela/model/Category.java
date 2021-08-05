package pl.jagiela.model;
//Klasa(główny model) reprezentująca kategorie
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Poniższe adnotacje oznaczają stworzenie w bazie danych tabeli o poniższcyh polach
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;//id kategorii (klucz podstawowy w tabeli category)

    @Column(name = "category_name")
    private String name;// nazwa kategorii

    private String description;//opis kategorii

    private String imgUrl;//pole przechowujące url do zdjęcia przedstawiającego kategorię.

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();// Pole to jest zawiere listę eventów przypisanych do danej kategorii
    //jest ono połączone z tabelą Event relacją OneToMany

    //konstruktor bezparametrowy
    public Category(){}

    //konstruktor przypisujące dane do pola name i description
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }


    //gettery i settery do powyższych pól
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
