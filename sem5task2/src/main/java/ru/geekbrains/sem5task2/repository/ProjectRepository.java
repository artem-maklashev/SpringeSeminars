package ru.geekbrains.sem5task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem5task2.model.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
