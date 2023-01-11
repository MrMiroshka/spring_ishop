package ru.miroshka.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.miroshka.market.api.models.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${CartServiceIntegration-getCurrentCart}")
    private String urlGetCurrentCart;
    @Value("${CartServiceIntegration-delAllProductsFromBasket}")
    private String urlDelAllProductsFromBasket;

    public Optional<CartDto> getCurrentCart() {
        Optional<CartDto> сartDto = Optional.ofNullable(restTemplate.getForObject(
                urlGetCurrentCart,
                CartDto.class));
        return сartDto;
    }


    public void delAllProductsFromBasket() {
       Optional.ofNullable(restTemplate.getForObject(
               urlDelAllProductsFromBasket, CartDto.class));

    }


}
