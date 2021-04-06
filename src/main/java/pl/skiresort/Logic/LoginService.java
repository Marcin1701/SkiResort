package pl.skiresort.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.Projection.CardPassReadModel;
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

    public boolean addUserCardPass(int id, CardPass cardPass){
        var usr = userRepository.findById(id);
        if (usr.isEmpty()){
            return false;
        }
        usr.get().setCardPass(cardPass);
        userRepository.save(usr.get());
        return true;
    }

    public CardPassReadModel getUserCardPass(Integer userId){
        var cardPass = userRepository.findById(userId).get().getCardPass();
        if (cardPass == null){
            return null;
        }
        else {
            return new CardPassReadModel(
                    cardPass.getStart(),
                    cardPass.getEnd(),
                    cardPass.getLevel()
            );
        }
    }

}
