package ru.miroshka.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miroshka.market.data.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);
}
