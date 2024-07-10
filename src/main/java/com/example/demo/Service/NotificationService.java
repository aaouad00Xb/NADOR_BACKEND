package com.example.demo.Service;
import com.example.demo.Dao.NotificationRepo;
import com.example.demo.Entities.MyUser;
import com.example.demo.Entities.Notification;
import com.example.demo.Entities.Section;
import com.example.demo.Entities.Situation;
import com.example.demo.Helpers.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

@Autowired
    private EmailService emailService;

    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private DateHelper dateHelper;


    public void createNotificationForUser(MyUser user, Situation situation, String content, Section section) {
        // Create a new notification for the user
        Notification notification = new Notification();
        notification.setIntitule("section "+ section.getIntitule() + ":  la situation du "  + dateHelper.formatLocalDateTime( dateHelper.convertToLocalDateTimeViaMilisecond(situation.getDatesituation()) )  + " a été bien validée");
        notification.setContent(content);
        notification.setSended_by("System");

        notification.setReceiver(user.getUsername());



        notificationRepo.save(notification);

        emailService.sendSimpleMail(notification.getContent(), notification.getIntitule(), user.getEmail());


     }

     public void createNotificationForUserinvalidate(MyUser user, Situation situation, String content, Section section) {
        // Create a new notification for the user
        Notification notification = new Notification();
        notification.setIntitule("section "+ section.getIntitule() + ":  la situation du "  + dateHelper.formatLocalDateTime( dateHelper.convertToLocalDateTimeViaMilisecond(situation.getDatesituation()) )  + " a été réfusé");
        notification.setContent(content);
        notification.setSended_by("System");

        notification.setReceiver(user.getUsername());



        notificationRepo.save(notification);

        emailService.sendSimpleMail(notification.getContent(), notification.getIntitule(), user.getEmail());


     }
}
