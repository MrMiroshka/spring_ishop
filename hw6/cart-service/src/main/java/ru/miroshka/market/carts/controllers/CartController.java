package ru.miroshka.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import ru.miroshka.market.carts.converters.ProductConverter;
import ru.miroshka.market.api.models.CartDto;
import ru.miroshka.market.carts.converters.CartConverter;
import ru.miroshka.market.carts.models.Cart;
import ru.miroshka.market.carts.servicies.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(this.cartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto putProductToCast(@PathVariable Long id) {
        this.cartService.putProductToCart(id);
        return cartConverter.entityToDto(this.cartService.getCurrentCart());
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
        return cartConverter.entityToDto(this.cartService.getCurrentCart());
    }

}
