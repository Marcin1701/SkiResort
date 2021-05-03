package pl.skiresort.Controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skiresort.Logic.OAuth2UserService;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Logic.ZxingQRGenerator;
import pl.skiresort.Model.Projection.UserReadModel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    ProfileController(final UserService userService, final OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.userService = userService;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/Oauth2")
    private String loginPage(Model model, OAuth2AuthenticationToken token) {
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(),
                token.getName()
        );
        model.addAttribute("user", userService.findUserByNameAndSurnameFormOneString(client.getPrincipalName()));
        return "profile";
    }

    @GetMapping("/{userId}")
    private String loginPage(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("user", new UserReadModel(userService.findUserById(userId)));
        model.addAttribute("cardPass", userService.getUserCardPass(userId));
        return "profile";
    }

    @GetMapping(value = "/generateQR/{id}", produces = "image/jpg")
    public @ResponseBody byte[] downloadQrCode(@PathVariable("id") Integer id, Model model, HttpServletResponse response) {
        try {
            var usr = userService.findUserById(id);
            ZxingQRGenerator zxingQRGenerator = ZxingQRGenerator.builder()
                    .setData(usr.getGeneratedcode().toString())
                    .setPath("qr.png")
                    .setHeight(200)
                    .setWidth(200)
                    .build();
            var image = zxingQRGenerator.createQrImage();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", stream);
            return stream.toByteArray();
        }
        catch(Exception e) {
            return null;
        }
    }

}
