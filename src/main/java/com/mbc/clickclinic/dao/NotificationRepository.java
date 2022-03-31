package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
