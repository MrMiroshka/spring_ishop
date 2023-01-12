package ru.miroshka.market.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.miroshka.market.api.dto.ProductDto;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    @Value("${ProductServiceIntegration-getProductById}")
    private String url;

    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        Optional<ProductDto> productDto = Optional.ofNullable(restTemplate.getForObject(
                url+id,
                ProductDto.class));
        return productDto;
    }

}
