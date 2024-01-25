package ru.geekbrains.sem5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Получение списка всех задач
     * @return список задач
     */
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    /**
     * Добавление задачи
     * @param task Задача
     * @return Добавленная задача
     */
    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    /**
     * Получение списка задач по статусу
     * @param status Необходимый статус задачи
     * @return Список задач с указанным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Task.TaskStatus status){
        return taskService.getTaskByStatus(status);
    }

    /**
     * Изменение статуса задачи
     * @param id Номер задачи для изменения статуса
     * @param task Измененная задача
     * @return Задача с измененным статусом
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable long id, @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task);
    }

    /**
     * Удаление задачи по номеру
     * @param id Номер задачи для удаления
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
    }
}
