package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);

    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User getUserByEmail(String email);

    User update(String email, User user);

    void deleteUser(Long id);

    NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email);
}
