package ru.geekbrains.seminar4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.seminar4.model.User;
import ru.geekbrains.seminar4.service.UserService;

/**
 * Класс обработки запросов к основно странице
 */
@Controller
public class MainPageController {
    private final UserService userService;
    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param model модель данных основной страницы
     *              включает в себя атрибуты: список поользователей и пользователя
     * @return отображение основной страницы
     */
    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "mainPage";
    }

}
