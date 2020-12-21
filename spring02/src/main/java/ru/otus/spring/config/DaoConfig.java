package ru.otus.spring.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Configuration
public class DaoConfig {
    private final String fileName;

    public DaoConfig(@Value("${application.fileName}") String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
