package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {


    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    /**
     * Сохранение пользователя из тела запроса
     * @param user пользователь
     * @return
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().save(user);
        return "User added from body!";
    }

    /**
     * Добавление пользователя из параметров запроса
     * @param name имя пользователя
     * @param age возраст пользователя
     * @param email электронная почта
     * @return результат добавления пользователя
     */
    @PostMapping("/params")
    public String userAddFromParam(
            @RequestParam(name="name", defaultValue = "") String name,
            @RequestParam(name="age", defaultValue = "0") String age,
            @RequestParam(name="email", defaultValue = "") String email
    ) {
        try {
            int userAge = Integer.parseInt(age);
            service.processRegistration(name, userAge, email);
            return "User added from params!";
        } catch (NumberFormatException e) {
            return "Can't add user. Wrong params";
        }
    }
}
