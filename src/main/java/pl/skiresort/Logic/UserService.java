package pl.skiresort.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.Projection.CardPassReadModel;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserReadModel findUserByNameAndSurnameFormOneString(String nameAndSurname) {
        char[] nameAndSurnameArray = nameAndSurname.toCharArray();
        boolean switchToSurname = false;
        String name = "";
        String surname = "";
        for (int i = 0; i < nameAndSurname.length(); i++) {
            if (nameAndSurnameArray[i] == ' ') {
                switchToSurname = true;
            }
            else if (!switchToSurname) {
                name = name.concat(String.valueOf(nameAndSurnameArray[i]));
            }
            else {
                surname = surname.concat(String.valueOf(nameAndSurnameArray[i]));
            }
        }
        return new UserReadModel(
                userRepository.findByNameAndSurname(name, surname)
                        .orElseThrow(
                                ()-> new UsernameNotFoundException("User does not exist!")));
    }

    public UserReadModel loginUser(UserWriteModel userWriteModel) {
        return userRepository.findByEmail(userWriteModel.getEmail()).map(UserReadModel::new).get();
    }

    public UserReadModel findUserByEmail(String email) {
        return new UserReadModel(
                userRepository.findByEmail(email)
                        .orElseThrow(
                                ()-> new UsernameNotFoundException("User does not exist!")));
    }

    public User findUserById(int id) {
        return userRepository.findById(id)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User does not exist!"));
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



    public UserReadModel save(final UserWriteModel user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("User already exist!");
        }
        var entity = user.toUser();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        // Encode password
        //entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        // Save encoded in database
        userRepository.save(entity);
        return new UserReadModel(entity);
    }


}
