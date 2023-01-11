package ru.miroshka.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miroshka.market.core.data.Order;
import ru.miroshka.market.core.data.User;

import java.util.List;


@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
