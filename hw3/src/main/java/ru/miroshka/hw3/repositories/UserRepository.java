package ru.miroshka.hw3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miroshka.hw3.data.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);
}
