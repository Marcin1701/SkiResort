package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skiresort.Logic.CardPassService;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Model.Projection.CardPassWriteModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/cardPass")
public class CardPassPurchaseController {

    private final CardPassService cardPassService;

    private final UserService userService;

    CardPassPurchaseController(final CardPassService cardPassService, final UserService userService) {
        this.cardPassService = cardPassService;
        this.userService = userService;
    }

    @GetMapping
    public String cardPassPage() {
        return "cardPassPurchase";
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
                              Model model){
       current.setUser(userService.findUser(id));
       //cardPassService.addCardPass(current);
       if (userService.addUserCardPass(id, current.toCardPass())) {
           model.addAttribute("purchaseMessage", "You have successfully purchased your card pass!");
       } else {
           model.addAttribute("purchaseMessage", "There was an error processing your purchase - check your credentials");
       }
       return "cardPassPurchase";
    }
}
