package ru.geekbrains.sem5task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem5task2.model.ProjectUsers;
import ru.geekbrains.sem5task2.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
