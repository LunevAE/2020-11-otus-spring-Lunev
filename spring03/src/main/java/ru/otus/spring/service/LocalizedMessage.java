package ru.otus.spring.service;

public interface LocalizedMessage {
    String getMessage(String key, Object... args);
}
