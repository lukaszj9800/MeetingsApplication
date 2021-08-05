package pl.jagiela.repository;
//repozytorium zapewniające połączeni między bazą danych a api do obsługi operacji związanych z tabelą zawierającą kategorie. Rozszerzone o JpaRepository które zawiera wiele metod wbudowanych do działania na bazie danych
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jagiela.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameContainingIgnoreCase(String name);
}
