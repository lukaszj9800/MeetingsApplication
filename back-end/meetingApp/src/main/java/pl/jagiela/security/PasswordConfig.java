package pl.jagiela.security;
//klasa konfigurująca sposób zapisu hasła do bazy danych(Hasło jest enkodowane)
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig
{
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);// algorytm endkodowania hasła korzystający z BCrypt
    }
}
