package pl.skiresort.Logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.skiresort.Model.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository mockUserRepository;

    @Test
    @DisplayName("Should throw UsernameNotFoundException")
    void findById_configurationOk_And_noUser_throwsUsernameNotFoundException() {

        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.empty());

        var testUserService = new UserService(mockUserRepository, null);

        var exception = catchThrowable(() -> testUserService.findUser(-1));

        assertThat(exception).isInstanceOf(UsernameNotFoundException.class);
    }
}
