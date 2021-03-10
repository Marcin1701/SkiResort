package pl.skiresort.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


// Starting page
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
