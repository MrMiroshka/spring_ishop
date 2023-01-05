package ru.miroshka.hw3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw3.data.User;
import ru.miroshka.hw3.servicies.OrderService;
import ru.miroshka.hw3.servicies.ProductService;
import ru.miroshka.hw3.servicies.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal/*,@RequestBody OrderData orderData*/){
        User user = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException());
        orderService.createOrder(user);
    }
}
