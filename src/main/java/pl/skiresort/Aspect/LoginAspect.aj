package pl.skiresort.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.skiresort.Logic.UserService;

@Aspect
@Component
class LoginAspect {

    private final UserService userService;

    LoginAspect(final UserService userService) {
        this.userService = userService;
    }

    @Around("execution(* pl.skiresort.Logic.UserService.findUser())")
    Object performUserValidation(ProceedingJoinPoint jp) throws Throwable {
        jp.proceed();
        return null;
    }
}
