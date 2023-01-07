package ru.miroshka.hw4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw4.converters.ProductConverter;
import ru.miroshka.hw4.data.Product;
import ru.miroshka.hw4.dto.ProductDto;
import ru.miroshka.hw4.servicies.ProductService;
import ru.miroshka.hw4.validators.ProductValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

/*    @GetMapping
    public List<Product> findAllProducts(){
        return productService.findAll();
    }*/

    @GetMapping("/{id}")
    public List<ProductDto> findProductById(@PathVariable Long id){
        List<ProductDto> lpDto= new ArrayList<>();
        lpDto.add(productConverter.entityToDto(productService.findById(id)));
        return lpDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto postProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        productDto.setId(null);
        Product product = this.productService.addProduct(productConverter.dtoToEntity(productDto));
        return productConverter.entityToDto(product);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        this.productService.changeProduct(productConverter.dtoToEntity(productDto));
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "name_product", required = false) String nameProduct,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        if (page < 1) {
            page = 1;
        }

        Page<ProductDto> d =this.productService.find(minCost, maxCost, nameProduct, page, pageSize).map(
                p->productConverter.entityToDto(p)
        );
        return d;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        this.productService.deleteById(id);
    }
}
