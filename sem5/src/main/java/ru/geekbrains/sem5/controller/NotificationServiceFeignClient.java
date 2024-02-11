package ru.geekbrains.sem5.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "http://localhost:8079")

public interface NotificationServiceFeignClient {
    @PostMapping("/sendNotification")
    void sendNotification(@RequestBody String notification);
}
