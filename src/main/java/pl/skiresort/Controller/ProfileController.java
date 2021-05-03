package pl.skiresort.Controller;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skiresort.Logic.UserService;
import pl.skiresort.Logic.ZxingQRGenerator;
import pl.skiresort.Model.Projection.UserReadModel;
import pl.skiresort.Model.Projection.UserWriteModel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    ProfileController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private String loginPage(Model model, @PathVariable final Integer userId) {
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
