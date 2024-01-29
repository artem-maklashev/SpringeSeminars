package ru.geekbrains.sem6task2.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem6task2.model.Characters;

/**
 * Репозиторий для хранения объекта Characters, полученного из запроса
 */
@Data
@Repository
@AllArgsConstructor
public class CharactersRepository {
    private Characters characters;
}
