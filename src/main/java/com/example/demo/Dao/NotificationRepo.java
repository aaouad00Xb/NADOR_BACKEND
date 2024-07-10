package com.example.demo.Dao;

import com.example.demo.Entities.Niveau;
import com.example.demo.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

}
