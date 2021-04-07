package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    LoginController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showLoginPage(@CookieValue(name="purchaseMessage", defaultValue = "No cookie") String message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("user", new UserWriteModel());
        return "login";
    }

    @PostMapping
    String loginUser(@ModelAttribute("users") UserWriteModel userWriteModel, Model model){
        var entity = userService.loginUser(userWriteModel);
        if (entity != null) {
            model.addAttribute("user", new UserReadModel(
                    entity.getId(), entity.getName(), entity.getSurname(), entity.getAge(), entity.getEmail(), entity.getPassword()));
            return "profile";
        }
        return "login";
    }

}
