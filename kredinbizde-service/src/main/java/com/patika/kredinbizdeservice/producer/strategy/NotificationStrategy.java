package com.patika.kredinbizdeservice.producer.strategy;

import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;

public interface NotificationStrategy {
    void sendNotification(NotificationDTO notification);
}
