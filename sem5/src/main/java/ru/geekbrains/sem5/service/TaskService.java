package ru.geekbrains.sem5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sem5.annotation.TrackUserAction;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.repository.TaskRepository;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Получение всех задач из репозитория
     * @return Список задач
     */
    @TrackUserAction
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    /**
     * Добавление задачи в репозиторий
     * @param task Добавляемая задача
     * @return Добавленая задача
     */
    @TrackUserAction
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Получение списка задач по статусу из репозитария
     * @param status Необходимый статус задачи
     * @return Список задач с указанным статусом
     */
    @TrackUserAction
    public List<Task> getTaskByStatus(Task.TaskStatus status) {
        return taskRepository.findTasksByTaskStatus(status);
    }

    /**
     * Обновление статуса задачи
     * @param id Номер задачи для изменения статуса
     * @param task Задача с измененным статусом
     * @return Задача с измененным статусом
     */
    @TrackUserAction
    public Task updateTaskStatus(long id, Task task) {
        Task oldTask = taskRepository.findById(id).orElse(null);
        if (oldTask != null){
            oldTask.setTaskStatus(task.getTaskStatus());
            return taskRepository.save(oldTask);
        }
        return null;
    }

    /**
     * Удаление задачи по номеру из репозитория
     * @param id Номер задачи для удаления
     */
    @TrackUserAction
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
