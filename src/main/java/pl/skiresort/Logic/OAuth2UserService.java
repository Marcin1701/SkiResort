package pl.skiresort.Logic;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.skiresort.Model.Provider;
import pl.skiresort.Model.User;
import pl.skiresort.Model.UserRepository;

import javax.servlet.http.HttpSession;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    OAuth2UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User validateUser(OAuth2User user) {
        var dbUser = userRepository.findByEmail(user.getAttribute("email"));
        if (dbUser.isEmpty()){
            return new User (
                    user.getAttribute("given_name"),
                    user.getAttribute("family_name"),
                    18,
                    user.getAttribute("email"),
                    "Google Verified"
            );
        }
        return null;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        if (user != null){
            var userEntity = validateUser(user);
            if (userEntity != null){
                userEntity.setProvider(Provider.GOOGLE);
                userRepository.save(userEntity);
            }
        }
        return new CustomOAuth2User(user);
    }

}
