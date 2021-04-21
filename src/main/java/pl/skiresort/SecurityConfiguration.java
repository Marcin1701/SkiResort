package pl.skiresort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.skiresort.Logic.OAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2UserService oAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth2/**", "/login", "/").permitAll()
                .anyRequest().permitAll()
                .and()
                //.formLogin()
                //    .loginPage("/login")
                //    .usernameParameter("email")
                //    .permitAll()
                //    .defaultSuccessUrl("/profile")
                //.and()
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/profile")
                    .userInfoEndpoint()
                    .userService(oAuth2UserService);

    }
}
