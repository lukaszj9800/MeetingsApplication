package pl.jagiela.model;
//Klasa(główny model) reprezentująca dane uwierzytelniające danego użytkownika
import javax.persistence.*;

//Poniższe adnotacje oznaczają stworzenie w bazie danych tabeli
@Entity
@Table(name = "UserAuth")
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Klucz główny tabeli UserAuth
    private String userName;//login
    private String password;//hasło
    private boolean active;//czy konto jest aktywne(zmienna logiczna jeśli jest równa false to zalogowanie się na to konto nie jest możliwe)
    private String roles;//role, uprawnienia danego użytkownika


    @OneToOne(mappedBy = "userAuth", fetch = FetchType.EAGER)
    private User user;//obiekt do którego przypisane są dane uwierzytelniające(relacja OneToOne z tabelą reprezentującą użytkowników)


    //gettery i settery
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
