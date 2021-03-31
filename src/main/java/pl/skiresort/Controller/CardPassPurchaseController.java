package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skiresort.Logic.CardPassService;
import pl.skiresort.Model.Projection.CardPassWriteModel;
import pl.skiresort.Model.Projection.UserWriteModel;
import pl.skiresort.Model.User;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/cardPass")
public class CardPassPurchaseController {

    private final CardPassService cardPassService;

    CardPassPurchaseController(final CardPassService cardPassService) {
        this.cardPassService = cardPassService;
    }

    @GetMapping
    public String getCardPassPage(Model model) {
        model.addAttribute("cardPass", new CardPassWriteModel());
        return "cardPassPurchase";
    }

    @PostMapping
    public String buyCardPass(@ModelAttribute("cardPass") @Valid CardPassWriteModel current, Model model){
        if (model.getAttribute("user") != null){
            current.setUser(((UserWriteModel) Objects.requireNonNull(model.getAttribute("user"))).toUser());
            cardPassService.addCardPass(current);
            return "cardPassPurchase";
        }
        else {
            return "login";
        }
    }
}
