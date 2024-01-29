package ru.geekbrains.sem6task2.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem6task2.model.Characters;
import ru.geekbrains.sem6task2.model.Info;
import ru.geekbrains.sem6task2.model.Results;
import ru.geekbrains.sem6task2.service.CharacterService;

@Data
@Repository
@AllArgsConstructor
public class CharactersRepository {
    private Characters characters;
}
