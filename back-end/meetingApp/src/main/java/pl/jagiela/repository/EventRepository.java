package pl.jagiela.repository;
//repozytorium zapewniające połączeni między bazą danych a api do obsługi operacji związanych z tabelą zawierającą eventy. Rozszerzone o JpaRepository które zawiera wiele metod wbudowanych do działania na bazie danych
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jagiela.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOwnerId(Long id);
}
