package com.example.notification.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @PostMapping("/sendNotification")
    public void sendNotification(@RequestBody String notification){
        System.out.println("Отправляем оповещение " + notification);
    }
}
