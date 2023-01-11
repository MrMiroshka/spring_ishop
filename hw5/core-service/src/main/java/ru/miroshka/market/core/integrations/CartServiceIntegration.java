package ru.miroshka.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.miroshka.market.api.models.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart() {
        Optional<CartDto> сartDto = Optional.ofNullable(restTemplate.getForObject(
                "http://localhost:8889/market-carts/api/v1/cart",
                CartDto.class));
        return сartDto;
    }


    public void delAllProductsFromBasket() {
       Optional.ofNullable(restTemplate.getForObject(
                "http://localhost:8889/market-carts/api/v1/cart/delete", CartDto.class));

    }


}
