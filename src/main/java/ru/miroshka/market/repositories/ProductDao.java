package ru.miroshka.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.miroshka.market.data.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
}
