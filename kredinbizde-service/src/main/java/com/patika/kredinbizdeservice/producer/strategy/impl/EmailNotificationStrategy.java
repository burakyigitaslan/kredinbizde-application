package com.patika.kredinbizdeservice.producer.strategy.impl;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.strategy.NotificationStrategy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Email notification sent: {}", notificationDTO);
    }
}
