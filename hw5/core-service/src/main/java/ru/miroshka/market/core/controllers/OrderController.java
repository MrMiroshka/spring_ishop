package ru.miroshka.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.market.core.converters.OrderConverter;
import ru.miroshka.market.core.data.Order;
import ru.miroshka.market.core.data.User;
import ru.miroshka.market.core.dto.OrderDto;
import ru.miroshka.market.core.servicies.OrderService;
import ru.miroshka.market.core.servicies.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal/*,@RequestBody OrderData orderData*/) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Пользователь такой не найден"));
        orderService.createOrder(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderDto> selectOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException());
        List<OrderDto> loDto = new ArrayList<>();

        for (Order order : orderService.selectOrders(user)) {
            loDto.add(orderConverter.entityToDto(order));
        }
        return loDto;
    }
}
