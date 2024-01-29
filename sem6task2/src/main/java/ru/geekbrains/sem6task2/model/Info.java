package ru.geekbrains.sem6task2.model;

import lombok.Data;

/**
 * Класс с информацией о количестве персонажей
 * количестве странице с персонажами
 * Наличии следующей страницы
 * Наличие предыдущей страницы
 */
@Data
public class Info {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
