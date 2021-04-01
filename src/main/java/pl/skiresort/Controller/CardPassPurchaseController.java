package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skiresort.Logic.CardPassService;
import pl.skiresort.Logic.LoginService;
import pl.skiresort.Model.Projection.CardPassWriteModel;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;

@Controller
@RequestMapping("/cardPass")
public class CardPassPurchaseController {

    private final CardPassService cardPassService;

    private final LoginService loginService;

    CardPassPurchaseController(final CardPassService cardPassService, final LoginService loginService) {
        this.cardPassService = cardPassService;
        this.loginService = loginService;
    }

    @GetMapping("/{id}")
    public String getCardPassPage(@PathVariable int id, Model model) {
        model.addAttribute("user", loginService.findUser(id));
        model.addAttribute("card", new CardPassWriteModel());
        return "cardPassPurchase";
    }

    @PostMapping("/{id}")
    public String buyCardPass(@ModelAttribute("card") CardPassWriteModel current,
                              @PathVariable int id){
       current.setUser(loginService.findUser(id));

       cardPassService.addCardPass(current);
       return "profile";
    }
}
