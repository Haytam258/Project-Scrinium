package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Notification;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    Notification saveNotification(Notification notification);
    void deleteNotification(Notification notification);
    Notification updateNotification(Notification notification);
}
