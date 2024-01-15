package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteUserById(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id);
    }


}
