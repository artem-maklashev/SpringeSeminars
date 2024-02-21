package ru.geekbrains.sem5.service.observer;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.sem5.model.Task;

@Component
public class TaskUpdateListener implements ApplicationListener<TaskUpdateEvent> {
    /**
     * Метод обработки при изменении статуса задачи
     * @param event изменение статуса задачи
     */
    @Override
    public void onApplicationEvent(TaskUpdateEvent event) {
        Task task = event.getTask();
        System.out.println("Слушатель получил уведомление об изменении статуса задачи " + task.getId() +" на " +
                task.getTaskStatus());
    }
}
