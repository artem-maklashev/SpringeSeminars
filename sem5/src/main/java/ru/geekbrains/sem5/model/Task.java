package ru.geekbrains.sem5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.NOT_STARTED ;
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    public enum TaskStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED;
    }
}
