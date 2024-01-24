package ru.geekbrains.sem4task2.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "h2.database")
public class H2DatabaseConfig {
    private String findAll;
    private String save;
    private String deleteUserById;
    private String findById;
    private String updateUser;
}
