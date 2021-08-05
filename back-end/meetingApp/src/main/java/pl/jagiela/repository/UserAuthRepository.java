package pl.jagiela.repository;
//repozytorium zapewniające połączeni między bazą danych a api do obsługi operacji związanych z tabelą zawierającą dane uwierzytelniające każdego użytkownika. Rozszerzone o JpaRepository które zawiera wiele metod wbudowanych do działania na bazie danych
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jagiela.model.UserAuth;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUserName(String userName);
    List<UserAuth> findUserAuthByUserName(String userName);

}
