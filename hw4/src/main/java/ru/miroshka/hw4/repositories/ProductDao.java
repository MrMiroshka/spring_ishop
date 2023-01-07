package ru.miroshka.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.miroshka.hw4.data.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
}
