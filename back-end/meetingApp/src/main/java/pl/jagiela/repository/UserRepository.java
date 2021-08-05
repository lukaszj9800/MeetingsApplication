package pl.jagiela.repository;
//repozytorium zapewniające połączeni między bazą danych a api do obsługi operacji związanych z tabelą zawierającą użytkowników. Rozszerzone o JpaRepository które zawiera wiele metod wbudowanych do działania na bazie danych
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jagiela.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstnameContainingIgnoreCase(String firstname);
    List<User> findByEventsId(Long id);
}
