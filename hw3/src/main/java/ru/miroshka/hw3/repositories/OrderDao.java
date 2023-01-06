package ru.miroshka.hw3.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.miroshka.hw3.data.Order;
import ru.miroshka.hw3.data.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderDao extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
