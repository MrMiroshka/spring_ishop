package ru.miroshka.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.market.data.Product;
import ru.miroshka.market.servicies.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public List<Product> findProductById(@PathVariable Long id){
        Product  slip =productService.findById(id).get();
        ArrayList<Product> as =  (new ArrayList<Product>());
        as.add(slip);
        return as;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
