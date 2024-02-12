package ru.geekbrains.sem5.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.sem5.model.Task;
import ru.geekbrains.sem5.repository.TaskRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class TaskServiceTest {
    @Test
    @DisplayName("Тест получения записей из репозитория")
    public void getAllTaskTest() {
        // Создаем мок-репозиторий
        TaskRepository taskRepository = mock(TaskRepository.class);

        // Создаем задачи
        Task task1 = new Task();
        Task task2 = new Task();

        // "Сохраняем" задачи в мок-репозитории (эмулируем сохранение)
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Устанавливаем ожидаемое поведение для метода findAll
        given(taskRepository.findAll()).willReturn(List.of(task1, task2));

        // Вызываем метод, который мы хотим протестировать
        List<Task> result = taskRepository.findAll();

        // Проверяем, что метод findAll был вызван один раз
        verify(taskRepository).findAll();

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(2, result.size(), "Должно быть две задачи в результате");
    }
}
