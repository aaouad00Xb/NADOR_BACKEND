package com.example.demo.Service;


import com.example.demo.Dao.UserRepository;
import com.example.demo.Entities.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService  {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }





    public void sendSimpleMail(String body, String subject,String reciepient) {
        // Create the verification link using the confirmation token
        String verificationLink = "http://10.0.0.3:8080/SST_Suivi/";


        SimpleMailMessage message = new SimpleMailMessage();
//        MyUser user = this.userRepository.findUserByUsername(reciepient).orElseThrow(()-> new RuntimeException("user"+"uername"+""+reciepient));


        message.setTo(reciepient);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);

            }
        }






