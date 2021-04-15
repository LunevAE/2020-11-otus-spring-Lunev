package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.NotificationGateway;
import ru.otus.spring.model.Customer;
import ru.otus.spring.model.Notification;
import ru.otus.spring.model.Order;
import ru.otus.spring.model.Parcel;
import ru.otus.spring.model.DeliveryType;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationGateway notifyGateway;

    @Override
    public Order notificationOrderRegistered(Order order) {
        String text = String.format("%s, your order №%s is registered.",
                order.getCustomer().getName(),
                order.getId()
        );
        sendNotification(text, order.getCustomer());
        return order;
    }

    @Override
    public Parcel notificationDeliveryStart(Parcel parcel) {
        String text = String.format("%s, your order №%s will be delivered %s",
                parcel.getOrder().getCustomer().getName(),
                parcel.getOrder().getId(),
                (parcel.getDeliveryType() == DeliveryType.LAND) ? "by train." : "by plane.");
        sendNotification(text, parcel.getOrder().getCustomer());
        return parcel;
    }

    @Override
    public void notificationSuccessfulDelivery(Parcel parcel) {
        String text = String.format("%s, your order №%s has been successfully delivered.",
                parcel.getOrder().getCustomer().getName(),
                parcel.getOrder().getId());
        sendNotification(text, parcel.getOrder().getCustomer());
    }

    private void sendNotification(String message, Customer customer) {
        Notification notification = Notification.builder()
                .email(customer.getEmail())
                .text(message)
                .build();
        notifyGateway.process(notification);
    }
}
