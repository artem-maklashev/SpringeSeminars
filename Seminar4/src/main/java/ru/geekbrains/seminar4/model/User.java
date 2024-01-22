package ru.geekbrains.seminar4.model;

/**
 * Класс пользователя
 */
public class User {
    private String userName;
    private String password;

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
