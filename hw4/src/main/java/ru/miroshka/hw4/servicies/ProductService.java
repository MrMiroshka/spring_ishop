package ru.miroshka.hw4.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.miroshka.hw4.data.Product;
import ru.miroshka.hw4.exceptions.ResourceNotFoundException;
import ru.miroshka.hw4.repositories.ProductDao;
import ru.miroshka.hw4.repositories.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Lazy
public class ProductService {
    private final ProductDao productDao;
    private final CartService cartService;


    public List<Product> findAll(){
        return productDao.findAll();
    }

    public static final Function<Product, Product> functionEntityToSoap = se ->{
        Product s = new Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setCost(se.getCost());
        return s;
    };

    public List<Product> getAllProducts(){
        return productDao.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }


    public Page<Product> find(Integer minCost, Integer maxCost, String nameProduct, Integer page, Integer pageSize) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualsThen(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductsSpecifications.costLesserOrEqualsThen(maxCost));
        }
        if (nameProduct != null) {
            spec = spec.and(ProductsSpecifications.nameLike(nameProduct));
        }
        return this.productDao.findAll(spec, PageRequest.of(page - 1, pageSize));

    }

    public List<Product> getProduct(Long id) {
        return this.productDao.findById(id).stream().toList();
    }

    public Optional<Product> findById(Long id) {
       // return productDao.findById(id);
        return Optional.ofNullable(productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("This product not found  id =  " + id)));
    }

    public Product addProduct(Product product) {
        return this.productDao.save(product);
    }

    public void deleteById(Long id) {
        this.productDao.deleteById(id);
        this.cartService.delProductCartById(id);
    }


    @Transactional
    public void changeProduct(Product product) {
        Product productChange = this.productDao.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("This product not found id - " + product.getId()));
        if (product.getCost() != null && product.getCost() > 0) {
            productChange.setCost(product.getCost());
        }
        if (!product.getTitle().isEmpty() && product.getTitle().length() > 3) {
            productChange.setTitle(product.getTitle());
        }
    }
}
