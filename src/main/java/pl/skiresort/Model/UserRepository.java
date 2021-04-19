package pl.skiresort.Model;

import org.springframework.data.repository.query.Param;
import pl.skiresort.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Integer i);

    User save(User entity);

    boolean existsById(Integer id);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

}
