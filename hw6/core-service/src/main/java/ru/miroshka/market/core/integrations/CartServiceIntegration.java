package ru.miroshka.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.miroshka.market.api.models.CartDto;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    @Value("${CartServiceIntegration-getCurrentCart}")
    private String urlGetCurrentCart;
    @Value("${CartServiceIntegration-delAllProductsFromBasket}")
    private String urlDelAllProductsFromBasket;

    public CartDto getCurrentCart() {
        CartDto cart =  cartServiceWebClient.get()
                .uri(urlGetCurrentCart)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

        return cart;
    }


    public void delAllProductsFromBasket() {
        cartServiceWebClient.get()
                .uri(urlDelAllProductsFromBasket)
                .retrieve()
                .toBodilessEntity()
                .block();
    }


}
