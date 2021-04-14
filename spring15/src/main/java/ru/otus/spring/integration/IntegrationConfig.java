package ru.otus.spring.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import ru.otus.spring.model.Notification;
import ru.otus.spring.model.Parcel;
import ru.otus.spring.model.DeliveryType;
import ru.otus.spring.service.DeliveryService;
import ru.otus.spring.service.NotificationService;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@RequiredArgsConstructor
public class IntegrationConfig {
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).get();
    }

    @Bean
    public QueueChannel ordersChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public MessageChannel notificationChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public MessageChannel deliveryChannel() { return MessageChannels.queue(50).get();}

    @Bean
    public MessageChannel landChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public MessageChannel airChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public IntegrationFlow notificationFlow() {
        return IntegrationFlows.from("notificationChannel")
                .<Notification>log("notification >", m -> Notification.notificationMessage.apply(m.getPayload()))
                .get();
    }

    @Bean
    public IntegrationFlow ordersFlow() {
        return IntegrationFlows.from("ordersChannel")
                .handle(notificationService, "notificationOrderRegistered")
                .channel("deliveryChannel")
                .get();
    }

    @Bean
    public IntegrationFlow deliveryFlow() {
        return IntegrationFlows.from("deliveryChannel")
                .handle(deliveryService, "setDeliveryType")
                .handle(notificationService, "notificationDeliveryStart")
                .<Parcel, DeliveryType>route(Parcel::getDeliveryType,
                        router -> router
                                .channelMapping(DeliveryType.LAND, "landChannel")
                                .channelMapping(DeliveryType.AIR, "airChannel")
                )
                .get();
    }

    @Bean
    public IntegrationFlow landFlow() {
        return IntegrationFlows.from("landChannel")
                .handle(notificationService, "notificationSuccessfulDelivery")
                .get();
    }

    @Bean
    public IntegrationFlow airFlow() {
        return IntegrationFlows.from("airChannel")
                .handle(notificationService, "notificationSuccessfulDelivery")
                .get();
    }
}
