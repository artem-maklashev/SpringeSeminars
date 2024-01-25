package ru.geekbrains.sem5task2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "project_users")
public class ProjectUsers extends EntityWithRelation{
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

}
