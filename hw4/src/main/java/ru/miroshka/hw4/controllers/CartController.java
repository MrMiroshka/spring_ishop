package ru.miroshka.hw4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw4.converters.ProductConverter;
import ru.miroshka.hw4.models.CartDto;
import ru.miroshka.hw4.servicies.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductConverter productConverter;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto getProductsCast() {
        return this.cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto putProductToCast(@PathVariable Long id) {
        //List<CartDtoItem> listProductInCart = this.cartService.putProductToCart(id);
        this.cartService.putProductToCart(id);
        return this.cartService.getCurrentCart();
    }

    @GetMapping("/delete/{id}")
    public void deleteProductBasket(@PathVariable Long id) {
        this.cartService.delProductCartById(id);
    }

    @GetMapping("/delete")
    public void deleteAllProductBasket() {
        this.cartService.delAllProductBasketById();
    }

    @GetMapping("/change")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto putProductToCast(
            @RequestParam(name = "productId", defaultValue = "0") Long id,
            @RequestParam(name = "count", defaultValue = "0") int count) {
        if (count == 1) {
            this.cartService.putProductToCart(id);
        } else if (count == -1) {
            this.cartService.delProductCartOneById(id);
        }
        return this.cartService.getCurrentCart();
    }

}
