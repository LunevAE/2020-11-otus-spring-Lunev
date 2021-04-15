package ru.otus.spring.model;

import lombok.Getter;

@Getter
public enum DeliveryType {
    LAND("train"),
    AIR("plane");

    private String name;
    DeliveryType(String name) {
        this.name = name;
    }

    public static DeliveryType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
