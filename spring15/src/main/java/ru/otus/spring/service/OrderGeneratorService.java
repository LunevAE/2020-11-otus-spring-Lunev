package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.OrderGateway;
import static ru.otus.spring.data.Data.getRandomOrder;

@Service
@RequiredArgsConstructor
public class OrderGeneratorService {
    private final OrderGateway shopGateway;

    @Scheduled(initialDelay = 2000, fixedRate = 1000)
    public void generateOrder() {
        shopGateway.process(getRandomOrder());
    }
}
