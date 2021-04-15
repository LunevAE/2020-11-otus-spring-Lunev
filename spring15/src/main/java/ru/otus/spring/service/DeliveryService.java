package ru.otus.spring.service;

import ru.otus.spring.model.Order;
import ru.otus.spring.model.Parcel;

public interface DeliveryService {
    Parcel setDeliveryType(Order order);
}
