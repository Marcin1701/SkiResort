package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    LoginController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showLoginPage(Model model) {
        model.addAttribute("user", new UserWriteModel());
        return "login";
    }

    @Transactional
    @PostMapping
    String loginUser(@ModelAttribute("user") UserWriteModel userWriteModel, Model model){
        var entity = userService.loginUser(userWriteModel);
        if (entity != null) {
            model.addAttribute("user", new UserReadModel(
                    entity.getId(), entity.getName(), entity.getSurname(), entity.getAge(), entity.getEmail(), entity.getPassword()));
            ResponseCookie userCredentialsCookie = ResponseCookie.from("userId", String.valueOf(entity.getId()))
                    .maxAge(60)
                    .build();
            ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, userCredentialsCookie.toString())
                    .build();
            return "profile";
        }
        return "login";
    }

}
