package ru.geekbrains.sem5.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.service.TaskService;
import ru.geekbrains.sem5.service.TaskToFileGateway;

import java.util.List;

@RestController
@RequestMapping("/api")
@EnableFeignClients
public class TaskController {
    private final TaskService taskService;
    private final MeterRegistry meterRegistry;
    private final TaskToFileGateway taskToFileGateway;

    @Autowired
    private NotificationServiceFeignClient feignClient;
    @Autowired
    public TaskController(TaskService taskService, MeterRegistry meterRegistry, TaskToFileGateway taskToFileGateway) {
        this.taskService = taskService;
        this.meterRegistry = meterRegistry;
        this.taskToFileGateway = taskToFileGateway;
    }

    /**
     * Получение списка всех задач
     * Создание собственной метрики - счетчик количества запросов к эндпоинту /tasks
     * @return список задач
     */
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        feignClient.sendNotification("Запрошены все задачи");
        meterRegistry.counter("get_all_tasks_counter").increment();
        return taskService.getAllTask();
    }

    /**
     * Добавление задачи
     * @param task Задача
     * @return Добавленная задача
     */
    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task){
        feignClient.sendNotification("добавлена задача " + task.getDescription());
        taskToFileGateway.writeTaskToFile("task_log.txt", task.toString());
        return taskService.addTask(task);
    }

    /**
     * Получение списка задач по статусу
     * @param status Необходимый статус задачи
     * @return Список задач с указанным статусом
     */
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Task.TaskStatus status){
        feignClient.sendNotification("Запрошены задачи со статусом " + status.toString());
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
