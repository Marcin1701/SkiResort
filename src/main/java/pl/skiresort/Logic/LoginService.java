package pl.skiresort.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    // Password encryption
    @Autowired
    private PasswordEncoder passwordEncoder;

    LoginService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadModel loginUser(UserWriteModel userWriteModel) {
        // Password decryption
        var entity = userRepository.findByEmailAndPassword(userWriteModel.getEmail(), userWriteModel.getPassword());
        return entity.map(UserReadModel::new).orElse(null);
    }

    public User findUser(int id) {
        var user = userRepository.findById(id);
        return user.orElse(null);
    }

}
