package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
class UserController {

    private final UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    UserController(final UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/users")
    ResponseEntity<List<User>> readAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping(value = "/users")
    ResponseEntity<User> createUser(@RequestBody @Valid User newUser) {
        logger.info("New User!");

        User result = repository.save(newUser);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
