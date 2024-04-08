package com.patika.kredinbizdeservice.producer.strategy;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NotificationContext {
    private NotificationStrategy strategy;

    public void sendNotification(NotificationDTO notification) {
        strategy.sendNotification(notification);
    }
}
