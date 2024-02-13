package ru.geekbrains.sem5.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.repository.TaskRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Тест получения всех задач")
    public void testGetAllTasks() {
        // Arrange
        List<Task> mockTasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(mockTasks);

        // Act
        List<Task> result = taskService.getAllTask();

        // Assert
        verify(taskRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Тест добавления задачи")
    public void testAddTask() {
        // Arrange
        Task taskToAdd = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(taskToAdd);

        // Act
        Task result = taskService.addTask(taskToAdd);

        // Assert
        verify(taskRepository).save(eq(taskToAdd));
        assertNotNull(result);
    }

    @Test
    @DisplayName("Тест получения задач по статусу")
    public void testGetTasksByStatus() {
        // Arrange
        Task.TaskStatus status = Task.TaskStatus.IN_PROGRESS;
        List<Task> mockTasks = Arrays.asList(new Task(), new Task());

        // Установим статус задачи
        mockTasks.forEach(task -> task.setTaskStatus(status));

        when(taskRepository.findTasksByTaskStatus(status)).thenReturn(mockTasks);

        // Act
        List<Task> result = taskService.getTaskByStatus(status);

        // Assert
        verify(taskRepository).findTasksByTaskStatus(eq(status));
        assertEquals(2, result.size());

        // Дополнительная проверка статусов задач в результирующем списке
        result.forEach(task -> assertEquals(status, task.getTaskStatus()));
    }

    @Test
    @DisplayName("Тест обновления статуса задачи")
    public void testUpdateTaskStatus() {
        // Arrange
        long taskId = 1;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskStatus(Task.TaskStatus.COMPLETED);

        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTaskStatus(Task.TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        // Act
        Task result = taskService.updateTaskStatus(taskId, updatedTask);

        // Assert
        verify(taskRepository).findById(eq(taskId));
        verify(taskRepository).save(eq(existingTask));
        assertNotNull(result);
        assertEquals(Task.TaskStatus.IN_PROGRESS, result.getTaskStatus());
    }

    @Test
    @DisplayName("Тест удаления задачи")
    public void testDeleteTask() {
        // Arrange
        long taskId = 1;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository).deleteById(eq(taskId));
    }
}
