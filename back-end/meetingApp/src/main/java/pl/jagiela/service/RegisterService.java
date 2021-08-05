package pl.jagiela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jagiela.model.Event;
import pl.jagiela.model.Register;
import pl.jagiela.model.User;
import pl.jagiela.model.UserAuth;
import pl.jagiela.repository.UserAuthRepository;
import pl.jagiela.repository.UserRepository;
import pl.jagiela.security.PasswordConfig;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private UserAuthRepository userAuthRepository;
    private PasswordConfig passwordConfig;

    @Autowired
    public RegisterService(UserRepository userRepository, UserAuthRepository userAuthRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
        this.passwordConfig = passwordConfig;
    }

    public void addUser(Register register){
        UserAuth auth = new UserAuth();
        User user = new User();

        auth.setUserName(register.getUserName());
        auth.setPassword(register.getPassword());
        auth.setPassword( passwordConfig.passwordEncoder().encode(auth.getPassword()));
        auth.setRoles("user:read");
        auth.setActive(true);

        user.setUserAuth(userAuthRepository.save(auth));
        user.setFirstname(register.getFirstname());
        user.setLastname(register.getLastname());
        user.setGender(register.getGender());
        user.setPhone(register.getPhone());
        user.setEmail(register.getEmail());
        if(user.getGender().equals("mężczyzna")){
            user.setImgUrl("https://bootdey.com/img/Content/avatar/avatar1.png");
        }else if(user.getGender().equals("kobieta")){
            user.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar8.png");
        }
        userRepository.save(user);
        System.out.println(auth.getUserName());

    }
}
