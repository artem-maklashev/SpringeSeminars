package ru.geekbrains.sem5task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem5task2.model.ProjectUsers;


import java.util.List;

@Repository
public interface UsersProjectRepository extends JpaRepository<ProjectUsers, Long> {
    /**
     * Получение списка записей по номеру проекта из БД
     * @param projectId Номер проекта
     * @return Сисок проектов с назначенными пользователями
     */
    List<ProjectUsers> findUsersByProjectId(Long projectId);

    /**
     * Получение списка проектов по номеру пользователя из БД
     * @param userId
     * @return
     */
    List<ProjectUsers> findAllByUserId(Long userId);

    /**
     * Проверка существования записи с проектом и назначенным пользователем а БД
     * @param userId Номер пользователя
     * @param projectId Номер проекта
     * @return true - если пользователь существует в этом проекте
     */
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);


}
