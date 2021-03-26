package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showUserPage(Model model) {
        model.addAttribute("user", new UserWriteModel());
        return "users";
    }

    @PostMapping
    String addUser(@ModelAttribute("users") UserWriteModel current, Model model) {
        // TODO JUNIT
        if (userService.existsByEmail(current.getEmail())){
            model.addAttribute("errorEmail", "Oops! Your email has been taken!");
            model.addAttribute("user", new UserWriteModel(current));
            return "users";
        }
        userService.save(new User(current.getName(), current.getSurname(), current.getAge(), current.getEmail()));
        model.addAttribute("user", new UserWriteModel());
        model.addAttribute("success", "You have created your new account!");
        model.addAttribute("errorEmail", null);
        return "users";
    }

}
