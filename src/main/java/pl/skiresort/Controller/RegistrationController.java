package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.UserWriteModel;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    RegistrationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showUserPage(Model model) {
        model.addAttribute("user", new UserWriteModel());
        return "register";
    }

    @PostMapping
    String addUser(@ModelAttribute("users") UserWriteModel current, Model model) {
        // TODO JUNIT
        // Sprawdzenie adresu email !! TODO
        logger.info("New user creation!");
        userService.save(current);
        model.addAttribute("user", new UserWriteModel());
        model.addAttribute("success", "You have created your new account!");
        model.addAttribute("errorEmail", null);
        return "register";
    }
}
