package ru.geekbrains.seminar4.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.seminar4.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс храрения даннх пользователей
 */
@Repository
public class UserRepository {
    private final List<User> userList;

    public UserRepository(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getAllUsers() {
        return userList;
    }
}
