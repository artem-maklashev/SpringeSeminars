package ru.geekbrains.sem5.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem5.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Поиск задач в БД по указанному статусу
     * @param taskStatus необходимый статус задач
     * @return Список задач
     */
    List<Task> findTasksByTaskStatus(Task.TaskStatus taskStatus);


}
