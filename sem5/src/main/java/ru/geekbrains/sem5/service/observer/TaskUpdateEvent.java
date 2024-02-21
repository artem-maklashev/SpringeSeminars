package ru.geekbrains.sem5.service.observer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.geekbrains.sem5.model.Task;

/**
 * Клас создания события при изменении статуса задачи
 */
@Getter
public class TaskUpdateEvent extends ApplicationEvent {
    private final Task task;

    public TaskUpdateEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

}
