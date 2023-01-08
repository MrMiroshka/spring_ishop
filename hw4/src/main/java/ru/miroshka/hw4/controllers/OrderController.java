package ru.miroshka.hw4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.hw4.converters.OrderConverter;
import ru.miroshka.hw4.data.Order;
import ru.miroshka.hw4.data.User;
import ru.miroshka.hw4.dto.OrderDto;
import ru.miroshka.hw4.servicies.OrderService;
import ru.miroshka.hw4.servicies.UserService;

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
