package ru.geekbrains.sem4task2.controllers;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.sem4task2.model.User;
import ru.geekbrains.sem4task2.service.UserService;

import java.util.List;


@Controller
@Log
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Вывод списка пользователей
     * @param model представление
     * @return отображает существующий список пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        log.info("Вывод пользователей в количестве " + users.size());
        return "user-list";
//        return "home.html";
    }

    /**
     * Обработка запроса на добавление пользователя
     * @param model представление
     * @return переход на форму создания нового пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        log.info("Переход на форму создания пользователя");
        return "user-create";
    }

    /**
     * Создание нового пользователя или редактирование существующего
     * @param user
     * @return переход на отображение списка пользователей
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        if (user.getId() == 0) {
            userService.saveUser(user);
            log.info("Добавление пользователя " + user);
        } else {
            userService.updateUser(user);
            log.info("Обновление пользователя " + user.getId());
        }
        return "redirect:/users";
    }

    /**
     * Обработка нажатия ссылки на удаление пользователя
     * @param id номер пользователя
     * @return переход на отображение списка пользователей
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable String id) {

        userService.deleteUser(id);
        log.info("Удаление пользователя под номером " + id);
        return "redirect:/users";
    }

    /**
     * Обработка изменения полей пользователя
     * @param id номер пользователя
     * @param model представление
     * @return переход на обработку создания/редактирования пользователя
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable String id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-create";
    }

}
