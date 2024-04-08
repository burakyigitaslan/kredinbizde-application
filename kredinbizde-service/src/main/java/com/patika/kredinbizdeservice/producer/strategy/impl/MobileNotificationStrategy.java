package com.patika.kredinbizdeservice.producer.strategy.impl;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.strategy.NotificationStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MobileNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Mobile notification sent: {}", notificationDTO);
    }
}
