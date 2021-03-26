package pl.skiresort.Logic;

import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPassRepository;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private CardPassRepository cardPassRepository;

    UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(final User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return null;
        }
        userRepository.save(user);
        return user;
    }

    public boolean existsByEmail(final String email){
        return userRepository.existsByEmail(email);
    }
}
