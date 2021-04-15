package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.DeliveryType;
import ru.otus.spring.model.Order;
import ru.otus.spring.model.Parcel;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public Parcel setDeliveryType(Order order) {
        Parcel parcel = new Parcel(order, DeliveryType.getRandom());
        return parcel;
    }
}
