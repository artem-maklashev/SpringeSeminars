package ru.geekbrains.sem5task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem5task2.model.ProjectUsers;


import java.util.List;

@Repository
public interface UsersProjectRepository extends JpaRepository<ProjectUsers, Long> {
    List<ProjectUsers> findUsersByProjectId(Long projectId);

    List<ProjectUsers> findProjectsByUserId(Long userId);

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
}
