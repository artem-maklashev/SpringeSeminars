package ru.geekbrains.sem4task2.repositories;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sem4task2.configuration.H2DatabaseConfig;
import ru.geekbrains.sem4task2.model.User;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final H2DatabaseConfig h2DatabaseConfig;

    public UserRepository(JdbcTemplate jdbc, H2DatabaseConfig h2DatabaseConfig) {
        this.jdbc = jdbc;
        this.h2DatabaseConfig = h2DatabaseConfig;
    }

    /**
     * Метод получения списка всех пользователей из БД
     * @return список пользователей
     */
    public List<User> findAll() {
//        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(h2DatabaseConfig.getFindAll(), userRowMapper);
    }

    /**
     * Метод добавления пользователя в БД
     * @param user пользователь
     * @return пользователь
     */
    public User save(User user) {
//        String sql = "INSERT INTO userTable (firstName, lastName) VALUES ( ?, ?)";
        jdbc.update(h2DatabaseConfig.getSave(), user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Метод удаления пользователя из БД по номеру
     * @param id номер пользователя
     */
    public void deleteUserById(String id) {
//        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(h2DatabaseConfig.getDeleteUserById(), id);
    }

    /**
     * Метод поиска пользователя по номеру
     * @param id номер пользователя
     * @return пользователь
     */
    public User findById(String id) {
//        String sql = "SELECT * FROM userTable WHERE id=?";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        try {
            return jdbc.queryForObject(h2DatabaseConfig.getFindById(), new Object[]{id}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void updateUser(User user) {
//        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(h2DatabaseConfig.getUpdateUser(), user.getFirstName(), user.getLastName(), user.getId());
    }
}


