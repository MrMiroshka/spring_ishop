package ru.miroshka.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miroshka.hw4.data.OrderItem;


public interface OrderItemsDao extends JpaRepository<OrderItem,Long> {
}
