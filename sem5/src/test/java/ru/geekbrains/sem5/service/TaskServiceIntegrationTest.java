package ru.geekbrains.sem5.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.repository.TaskRepository;
import ru.geekbrains.sem5.service.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTask() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        // Act
        List<Task> result = taskService.getAllTask();

        // Assert
        verify(taskRepository).findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testAddTask() {
        // Arrange
        Task taskToAdd = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(taskToAdd);

        // Act
        Task result = taskService.addTask(taskToAdd);

        // Assert
        verify(taskRepository).save(eq(taskToAdd));
        assertEquals(taskToAdd, result);
    }

    @Test
    public void testGetTaskByStatus() {
        // Arrange
        Task.TaskStatus status = Task.TaskStatus.IN_PROGRESS;
        Task task = new Task();
        task.setTaskStatus(status);
        when(taskRepository.findTasksByTaskStatus(status)).thenReturn(Collections.singletonList(task));

        // Act
        List<Task> result = taskService.getTaskByStatus(status);

        // Assert
        verify(taskRepository).findTasksByTaskStatus(eq(status));
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateTaskStatus() {
        // Arrange
        long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskStatus(Task.TaskStatus.IN_PROGRESS);

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
        assertEquals(updatedTask, result);
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        long taskId = 1L;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository).deleteById(eq(taskId));
    }
}
