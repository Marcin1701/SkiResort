package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.UserRepository;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    ProfileController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private String loginPage(Model model, @PathVariable final Integer userId) {
        model.addAttribute("user", new UserReadModel(userService.findUser(userId)));
        model.addAttribute("cardPass", userService.getUserCardPass(userId));
        return "profile";
    }

}
