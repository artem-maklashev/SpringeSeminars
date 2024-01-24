package ru.geekbrains.sem4task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sem4task2.model.User;
import ru.geekbrains.sem4task2.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Метод передачи пользователя из контроллера в репозиторий для сохранения
     * @param user пользователь
     * @return пользователь
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Метод передачи номера пользователя из контроллера в репозиторий для удаления
     * @param id номер пользователя
     */
    public void deleteUser(String id) {
        userRepository.deleteUserById(id);
    }

    /**
     * Метод передачи измененного пользователя от контрллера в репозиторий для изменения
     * @param user
     */
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    /**
     * Метод передачи запроса на поиск пользователя по номеру от контроллера к репозиторию
     * @param id номер пользователя
     * @return пользователь
     */
    public User getUser(String id) {
        return userRepository.findById(id);
    }


}
