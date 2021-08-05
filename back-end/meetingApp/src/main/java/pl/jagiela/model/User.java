package pl.jagiela.model;

//Klasa(główny model) reprezentująca użytkowników
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Poniższe adnotacje oznaczają stworzenie w bazie danych tabeli
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;//id użytkownika które jest kluczem głównym w tabeli user

    @Column(name = "firstname")
    private String firstname;//imię

    @Column(name = "lastname")
    private String lastname;//nazwisko

    @Column(name = "gender")
    private String gender;//płeć

    @Column(name = "imgUrl")
    private String imgUrl;// url do obrazka użytkownika, który ustawiany jest "na sztywno"

    @Column(name = "email")
    private String email;//email

    @Column(name = "phone")
    private String phone;//telefon

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private UserAuth userAuth;// obiekt reprezentujący dane do uwierzytelniania danego użytkownika. Pole to połączone jest relacją OneToOne z obiektem UserAuth


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();// lista eventów w których dany użytkownik uczestniczy. Pole połączone jest relacją ManyToMany z tabelą reprezentującą eventy

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Column(name = "ownEvents")
    private List<Event> ownEvents;// lista eventów których właścicielem jest dany użytkownik

    public User(){}//konstruktor bezparametrowy

    //konstruktor
    public User(String firstname, String lastname, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }


    //gettery i settery
    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getOwnEvents() {
        return ownEvents;
    }

    public void setOwnEvents(List<Event> ownEvents) {
        this.ownEvents = ownEvents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Imie:" +firstname + lastname;
    }
}
