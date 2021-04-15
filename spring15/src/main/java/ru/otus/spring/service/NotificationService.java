package ru.otus.spring.service;

import ru.otus.spring.model.Order;
import ru.otus.spring.model.Parcel;

public interface NotificationService {
    Order notificationOrderRegistered(Order order);
    Parcel notificationDeliveryStart(Parcel parcel);
    void notificationSuccessfulDelivery(Parcel parcel);
}
