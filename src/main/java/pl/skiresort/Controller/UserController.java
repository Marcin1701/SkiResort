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

// Starting page
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showUserPage(Model model) {
        model.addAttribute("user", new UserWriteModel());
        return "users";
    }

    @PostMapping
    String addUser(@ModelAttribute("users") UserWriteModel current, Model model) {
        if (userService.existsByEmail(current.getEmail())){
            return "error";
        }
        userService.save(new User(current.getName(), current.getSurname(), current.getAge(), current.getEmail()));
        model.addAttribute("user", new UserWriteModel());
        return "users";
    }

}
