package ru.miroshka.hw3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw3.converters.OrderConverter;
import ru.miroshka.hw3.converters.ProductConverter;
import ru.miroshka.hw3.data.Order;
import ru.miroshka.hw3.data.User;
import ru.miroshka.hw3.dto.OrderDto;
import ru.miroshka.hw3.dto.ProductDto;
import ru.miroshka.hw3.servicies.OrderService;
import ru.miroshka.hw3.servicies.ProductService;
import ru.miroshka.hw3.servicies.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal/*,@RequestBody OrderData orderData*/){
        User user = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException());
        orderService.createOrder(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderDto>  selectOrders(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException());
        List<OrderDto> loDto= new ArrayList<>();

        for(Order order:orderService.selectOrders(user)){
            loDto.add(orderConverter.entityToDto(order));
        }
        return loDto;
    }
}
