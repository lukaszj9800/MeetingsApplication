package pl.jagiela.auth;
//Serwis obsługujący klasę ApplicationUser
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jagiela.model.UserAuth;
import pl.jagiela.repository.UserAuthRepository;

@Service
public class ApplicationUserService implements UserDetailsService
{
    @Autowired
    private UserAuthRepository userAuthRepository;//Wstrzykniecie klasy Repozytorium która umożliwia operacje na bazie danych.


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthRepository.findByUserName(s)
                .orElseThrow(IllegalAccessError::new);
        return new ApplicationUser(userAuth);
    }
}
