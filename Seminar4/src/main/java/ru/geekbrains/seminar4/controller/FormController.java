package ru.geekbrains.seminar4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.seminar4.model.User;
import ru.geekbrains.seminar4.service.UserService;

/**
 * Обработчик запросов к форме добавления нового пользователя
 */
@Controller
public class FormController {

    private final UserService userService;
    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Добавление нового пользователя из формы ввода
     * @param user пользователь
     * @return возврат на основную страницу
     */
    @PostMapping("/form")
    public String processForm(@ModelAttribute User user){
        System.out.println("Username: " + user.getUserName());
        System.out.println("Password: " + user.getPassword());
        userService.addUser(user);
        System.out.println(userService.getAllUsers().size());
        return "redirect:/";
    }
}
