package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationProducer notificationProducer;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);

        verify(notificationProducer, times(3)).sendNotification(any(NotificationDTO.class));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getAllUsers_Success() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> retrievedUsers = userService.getAllUsers();

        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_Success() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(id);

        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserByEmail_Success() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserByEmail(email);

        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser);

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getUserByEmail_UserNotFound() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(KredinbizdeException.class, () -> userService.getUserByEmail(email));

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void updateUser_Success() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.update(email, user);

        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_Success() {
        Long id = 1L;
        doNothing().when(userRepository).deleteById(id);

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}
