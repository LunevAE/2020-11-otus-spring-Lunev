package ru.otus.spring.data;

import com.google.common.collect.ImmutableList;
import ru.otus.spring.model.Customer;
import ru.otus.spring.model.Order;
import ru.otus.spring.model.OrderItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Data {

    private final static AtomicLong ID = new AtomicLong(1);
    private static Random rand = new Random();

    private final static List<OrderItem> orderItems = ImmutableList.of(
            new OrderItem("Pencil", 3),
            new OrderItem("Book", 12),
            new OrderItem("Owl", 100)
    );

    private final static List<Customer> customers = ImmutableList.of(
            new Customer("Harry Potter","wizard@mail.ru"," Cupboard Under the Stairs, 4 Privet Drive"),
            new Customer("Peter Parker","spiderman@gmail.com","139 street, New York")
    );

    public static Order getRandomOrder(){
        Customer customer = customers.get(rand.nextInt(customers.size()));
        Map<OrderItem, Integer> m = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            OrderItem orderItem = orderItems.get(rand.nextInt(orderItems.size()));
            m.put(orderItem, rand.nextInt(orderItems.size()));
        }
        return Order.builder()
                .id(ID.getAndIncrement())
                .customer(customer)
                .items(m)
                .build();
    }
}
