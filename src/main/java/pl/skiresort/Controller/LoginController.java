package pl.skiresort.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.LoginService;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    String showLoginPage(Model model) {
        model.addAttribute("user", new UserWriteModel());
        return "login";
    }

    @PostMapping
    String loginUser(@ModelAttribute("users") UserWriteModel userWriteModel, Model model){
        var entity = loginService.loginUser(userWriteModel);
        if (entity != null) {
            model.addAttribute("user", new UserReadModel(
                    entity.getId(), entity.getName(), entity.getSurname(), entity.getAge(), entity.getEmail(), entity.getPassword()));
            return "profile";
        }
        return "login";
    }

}
