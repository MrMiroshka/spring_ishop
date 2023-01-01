package ru.miroshka.hw2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw2.converters.ProductConverter;
import ru.miroshka.hw2.data.Product;
import ru.miroshka.hw2.dto.ProductDto;
import ru.miroshka.hw2.servicies.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductConverter productConverter;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<ProductDto> getProductCast(
    ) {
        List<Product> listProduct = this.cartService.getProductsFromBasket();
        List<ProductDto> listProductDto = new ArrayList<>();
        for (Product p: listProduct) {
            listProductDto.add(productConverter.entityToDto(p));
        }
        return new PageImpl<>(listProductDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<ProductDto> putProductToCast(@PathVariable Long id) {
        List<Product> listProduct = this.cartService.putProductToCart(id);
        List<ProductDto> listProductDto = new ArrayList<>();
        for (Product p: listProduct) {
            listProductDto.add(productConverter.entityToDto(p));
        }
        return new PageImpl<>(listProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductBasket(@PathVariable Long id) {
        this.cartService.delProductBasketById(id);
    }

}
