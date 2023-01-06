package ru.miroshka.hw3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miroshka.hw3.data.OrderItem;


public interface OrderItemsDao extends JpaRepository<OrderItem,Long> {
}
