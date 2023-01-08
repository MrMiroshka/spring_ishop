package ru.miroshka.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miroshka.hw4.data.Order;
import ru.miroshka.hw4.data.User;

import java.util.List;


@Repository
public interface OrderDao extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
