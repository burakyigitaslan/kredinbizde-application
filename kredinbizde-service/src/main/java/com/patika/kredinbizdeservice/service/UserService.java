package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.UserRepository;
import com.patika.kredinbizdeservice.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final NotificationProducer notificationProducer;

    @CacheEvict(value = "users", allEntries = true)
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = KredinbizdeException.class)
    @Override
    public User save(User user) {
        System.out.println(user.hashCode());
        user.setIsActive(true);
        User savedUser = userRepository.save(user);

        notificationProducer.sendNotification(prepareNotificationDTO(NotificationType.EMAIL, user.getEmail()));

        return savedUser;
    }

    @Override
    public NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi.")
                .notificationType(notificationType)
                .email(email)
                .build();
    }

    @Cacheable(value = "users")
    @Override
    public List<User> getAllUsers() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Cacheable(value = "users", key = "#email")
    @Override
    public User getUserByEmail(String email) {

        log.info("user db'den alındı.");

        Optional<User> foundUser = userRepository.findByEmail(email);

        return foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));
    }

    @CachePut(value = "users", key = "#email")
    @Override
    public User update(String email, User user) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        User fUser = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));

        user.setId(fUser.getId());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
