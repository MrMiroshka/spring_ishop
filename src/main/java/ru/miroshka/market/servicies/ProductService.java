package ru.miroshka.market.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.miroshka.market.data.Product;
import ru.miroshka.market.repositories.ProductDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> findAll(){
        return productDao.findAll();
    }


    public Optional<Product> findById(Long id) {
        return productDao.findById(id);
    }

    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}
