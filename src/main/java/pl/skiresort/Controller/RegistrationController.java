package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.RegistrationService;
import pl.skiresort.Model.Projection.UserWriteModel;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
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
        registrationService.save(current);
        model.addAttribute("user", new UserWriteModel());
        model.addAttribute("success", "You have created your new account!");
        model.addAttribute("errorEmail", null);
        return "register";
    }
}
