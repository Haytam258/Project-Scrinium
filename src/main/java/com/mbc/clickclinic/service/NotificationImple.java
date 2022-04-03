package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.NotificationRepository;
import com.mbc.clickclinic.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationImple implements NotificationService{

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationImple(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.saveAndFlush(notification);
    }

    @Override
    public void deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return null;
    }
}
