package pl.skiresort.Controller;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skiresort.Logic.CardPassService;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.CardPassWriteModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/cardPass")
public class CardPassPurchaseController {

    private final UserService userService;

    CardPassPurchaseController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getCardPassPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("card", new CardPassWriteModel());
        return "cardPassPurchase";
    }

    @PostMapping("/{id}")
    public String buyCardPass(@ModelAttribute("card") CardPassWriteModel current,
                              @PathVariable int id,
                              BindingResult bindingResult,
                              Model model
                              )
    {
        if (bindingResult.hasErrors()){
            return "cardPassPurchase";
        }
       current.setUser(userService.findUser(id));
       if (userService.addUserCardPass(id, current.toCardPass())) {
           return "cardPassInfo";
       }

       return "cardPassInfo";
    }
}
