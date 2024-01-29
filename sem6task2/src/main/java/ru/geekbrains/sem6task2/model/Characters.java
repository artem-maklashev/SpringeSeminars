package ru.geekbrains.sem6task2.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Characters {
    private Info info;
    private List<Character> results;
}
