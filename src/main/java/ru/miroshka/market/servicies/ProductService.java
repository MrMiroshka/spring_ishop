package ru.miroshka.market.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.miroshka.market.data.Product;
import ru.miroshka.market.repositories.ProductDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> findAll(){
        return productDao.findAll();
    }
}
