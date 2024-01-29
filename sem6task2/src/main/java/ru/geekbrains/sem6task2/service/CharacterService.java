package ru.geekbrains.sem6task2.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sem6task2.model.Character;
import ru.geekbrains.sem6task2.model.Characters;
import ru.geekbrains.sem6task2.repository.CharactersRepository;



@Service
@Data
public class CharacterService {
    private CharactersRepository charactersRepository;
    @Autowired
    public CharacterService(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    public String getPrevPage() {
        return charactersRepository.getCharacters().getInfo().getPrev();
    }
    public String getNextPage() {
        return charactersRepository.getCharacters().getInfo().getNext();
    }

    public void setCharacters(Characters characters) {
        charactersRepository.setCharacters(characters);
    }

    public Character getCharacterById(int id) {
        return charactersRepository.getCharacters()
                .getResults()
                .stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
