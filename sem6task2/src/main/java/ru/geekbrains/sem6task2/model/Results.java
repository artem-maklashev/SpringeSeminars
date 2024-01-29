package ru.geekbrains.sem6task2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
    private List<Character> results;
}
