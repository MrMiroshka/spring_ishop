package ru.miroshka.hw3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miroshka.hw3.data.Order;


@Repository
public interface OrderDao extends JpaRepository<Order,Long> {
}
