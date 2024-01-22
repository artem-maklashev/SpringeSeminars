package ru.geekbrains.seminar4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.seminar4.model.User;
import ru.geekbrains.seminar4.repository.UserRepository;

import java.util.List;

/**
 * Класс для взаимодействия хранилища и обработчиков
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * добавление пользователя в хранилище
     * @param user пользователь
     */
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    /**
     * Получение всех пользователей из хранилища
     * @return список поьлзователей
     */
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
