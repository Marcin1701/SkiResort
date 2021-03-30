package pl.skiresort.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPassRepository;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    // Password encryption
    @Autowired
    private PasswordEncoder passwordEncoder;

    RegistrationService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadModel save(final UserWriteModel user){
        var entity = user.toUser();
        if (userRepository.existsByEmail(entity.getEmail())) {
           return null;
        }
        // Encode password
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        // Save encoded in database
        userRepository.save(entity);
        return new UserReadModel(entity);
    }

    public boolean existsByEmail(final String email){
        return userRepository.existsByEmail(email);
    }

}
