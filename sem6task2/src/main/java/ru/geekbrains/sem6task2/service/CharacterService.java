package ru.geekbrains.sem6task2.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sem6task2.model.Character;
import ru.geekbrains.sem6task2.model.Characters;
import ru.geekbrains.sem6task2.repository.CharactersRepository;


/**
 * Класс для работы с персонажами и их репозиторием
 */
@Service
@Data
public class CharacterService {
    private CharactersRepository charactersRepository;
    @Autowired
    public CharacterService(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    /**
     * Метод для получения ссылки на предыдущую страницу для пагинации.
     * @return ссылка на предыдущую страницу для пагинации или null.
     */
    public String getPrevPage() {
        return charactersRepository.getCharacters().getInfo().getPrev();
    }

    /**
     * Метод для получения ссылки на следующую страницу для пагинации.
     * @return ссылка на следующую страницу для пагинации или null.
     */
    public String getNextPage() {
        return charactersRepository.getCharacters().getInfo().getNext();
    }

    /**
     * Метод для записи персонажей в репозиторий.
     * @param characters персонажи для записи в репозиторий.
     */
    public void setCharacters(Characters characters) {
        charactersRepository.setCharacters(characters);
    }

    /**
     * Метод получения детальных данных о персонаже из репозитория.
     * @param id идентификатор персонажа.
     * @return детальные данные о персонаже или null.
     */
    public Character getCharacterById(int id) {
        return charactersRepository.getCharacters()
                .getResults()
                .stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
