package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationProducer notificationProducer;

    @Test
    public void should_create_user_successfully() {

        //given
        //Mockito.when(userRepository.save(praperaUser())).thenReturn(praperaUser()); //neden hata veriyor?

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(preparaUser()); //neden hata vermiyor
        System.out.println(preparaUser().hashCode());
        //when
        User userResponse = userService.save(preparaUser());

        //then --assertion -- doğrulama

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo("test name");
        assertThat(userResponse.getSurname()).isEqualTo(preparaUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(preparaUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(preparaUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository, times(1)).save(Mockito.any(User.class));
        verify(notificationProducer, times(1)).sendNotification(Mockito.any(NotificationDTO.class));
    }

    @Test
    public void should_return_user_by_email_successfully() {

        //given
        Mockito.when(userRepository.findByEmail(preparaUser().getEmail())).thenReturn(Optional.of(preparaUser()));

        //when
        User userResponse = userService.getUserByEmail("test@gmail.com");

        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getName()).isEqualTo("test name");
        assertThat(userResponse.getSurname()).isEqualTo(preparaUser().getSurname());
        assertThat(userResponse.getEmail()).isEqualTo(preparaUser().getEmail());
        assertThat(userResponse.getPassword()).isEqualTo(preparaUser().getPassword());
        assertThat(userResponse.getIsActive()).isTrue();

        verify(userRepository, times(1)).findByEmail("test@gmail.com");
    }


    @Test
    public void should_throw_kredinbizdeException_whenUserNotFound() {
        //given

        //when
        Throwable throwable = catchThrowable(() -> userService.getUserByEmail("test@gmail.com"));

        //then
        assertThat(throwable).isInstanceOf(KredinbizdeException.class);
        assertThat(throwable.getMessage()).isEqualTo("user bulunamadı!");
    }

    @Test
    public void should_throw_kredinbizdeException_whenUserNotFound2() {
        //given

        //when
        Assertions.assertThrows(KredinbizdeException.class, () -> userService.getUserByEmail("test"), "user bulunamadı!");

    }

    private User preparaUser() {
        User user = new User();

        user.setName("test name");
        user.setSurname("test surname");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setIsActive(true);
        return user;
    }

}
