package ru.miroshka.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.miroshka.market.api.models.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    @Value("${CartServiceIntegration-getCurrentCart}")
    private String urlGetCurrentCart;
    @Value("${CartServiceIntegration-delAllProductsFromBasket}")
    private String urlDelAllProductsFromBasket;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(cartServiceWebClient.get()
                .uri(urlGetCurrentCart)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block()
    );
    }


    public void delAllProductsFromBasket() {
        cartServiceWebClient.get()
                .uri(urlDelAllProductsFromBasket)
                .retrieve()
                .toBodilessEntity()
                .block();
    }


}
