package ru.geekbrains.sem6task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.sem6task2.model.Character;
import ru.geekbrains.sem6task2.model.Characters;
import ru.geekbrains.sem6task2.service.CharacterService;


@Controller
public class CharactersController {
    @Value("${rickandmorty.api.url}")
    private String apiUrl;
    @Autowired
    private CharacterService characterService;

    /**
     * Метод для получения данных о персонажах из API и отображения их на странице.
     * @param model Модель для отображения данных.
     * @return Название шаблона для отображения.
     */
    @GetMapping("/")
    public String getCharacters(Model model) {
        Characters characters = fetchData(apiUrl);
        characterService.setCharacters(characters);
        model.addAttribute("characters", characters.getResults());
        model.addAttribute("info", characters.getInfo());
        return "mainPage";
    }

    /**
     * Переход на предыдущую страницу персонажей.
     * @return Название шаблона для отображения.
     */
    @GetMapping("/characters/previous")
    public String getPreviousCharacters() {
        String url = characterService.getPrevPage();
        if (url != null) {
            apiUrl = url;
        }
        return "redirect:/";
    }

    /**
     * Переход на следующую страницу персонажей.
     * @return Название шаблона для отображения.
     */
    @GetMapping("/characters/next")
    public String getNextCharacters() {
        String url = characterService.getNextPage();
        if (url != null) {
            apiUrl = url;
        }
        return "redirect:/";
    }

    /**
     * Метод для получения данных о персонажах из API.
     * @param url URL для запроса.
     * @return Объект Characters с данными о персонажах.
     */
    private Characters fetchData(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Characters.class);
    }

    /**
     * Метод для получения детальной информации о персонаже.
     * @param id ID персонажа.
     * @param model Модель для отображения данных.
     * @return Название шаблона для отображения.
     */
    @GetMapping("/characters/details/{id}")
    public String getCharacterDetails(@PathVariable int id, Model model) {
        Character character = characterService.getCharacterById(id);
        if (character != null) {
            model.addAttribute("character", character);
            return "characterDetails";
        }
        return "redirect:/";
    }
}
