package ru.geekbrains.sem5task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.sem5task2.model.Project;
import ru.geekbrains.sem5task2.model.User;
import ru.geekbrains.sem5task2.repository.UsersProjectRepository;
import ru.geekbrains.sem5task2.service.UserProjectService;

import java.util.List;

@Controller
public class UserProjectController {
    private final UserProjectService usersProjectService;
    @Autowired
    public UserProjectController(UserProjectService usersProjectService) {
        this.usersProjectService = usersProjectService;
    }

    /**
     * Обработка запроса на получение всех пользователей определенного проекта
     * @param id Номер проекта
     * @return список пользователей
     */
    @GetMapping("/usersOfProject/{id}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable Long id) {
        List<User> users = usersProjectService.getUsersByProjectId(id);
        return ResponseEntity.ok(users);
    }

    /**
     * Обработка запроса на получение всех проектов определенного пользователя
     * @param id Номер пользователя
     * @return Список проектов
     */
    @GetMapping("/projectsOfUser/{id}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long id) {
        List<Project> projects = usersProjectService.getProjectsByUserId(id);
        return ResponseEntity.ok(projects);
    }

    /**
     * Обработка запроса на добавление пользователя к проекту
     * @param userId Номер пользователя
     * @param projectId номер проекта
     * @return Результат выполненгия операци
     */
    @PostMapping("/addUserToProject/{userId}/{projectId}")
    public ResponseEntity<String> addUserToProject(@PathVariable Long userId, @PathVariable Long projectId){
        usersProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok(String.format("Пользователь %s добавлен к проекту %s", userId, projectId));
    }

    /**
     * Обработка запроса на удаление определенного пользователя из проекта
     * @param userId Номер пользователя
     * @param projectId номер проекта
     * @return Результат выполненгия операци
     */
    @PostMapping("/remove-user/{userId}/from-project/{projectId}")
    public ResponseEntity<String> removeUserFromProject(
            @PathVariable Long userId,
            @PathVariable Long projectId) {
        usersProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok(String.format("Пользователь %s удален из проекта %s", userId, projectId));
    }
}