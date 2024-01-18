package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    public UserRepository getRepository() {
        return repository;
    }

    @Autowired
    private UserRepository repository;

    /**
     * Сортировка пользователей в порядке возрастания
     *
     * @return Отсортированный список пользователей
     */
    public List<User> sortUsersByAge() {
        return repository.getUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Фильтрация пользователей по возрасту
     *
     * @param age возраст
     * @return Список плользователей старше указанного возраста
     */
    public List<User> filterUsersByAge(int age) {
        return repository.getUsers().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Вычисление среднего возраста пользователей
     *
     * @return средний возраст пользователей
     */
    public double calculateAverageAge() {
        return repository.getUsers().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    /**
     * Добавление пользователя
     * @param user пользователь
     */
    public void addUserToList(User user) {
        repository.save(user);
    }


}
