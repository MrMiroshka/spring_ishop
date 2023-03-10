package ru.miroshka.market.converters;

import org.springframework.stereotype.Component;
import ru.miroshka.market.data.Product;
import ru.miroshka.market.dto.ProductDto;

import java.util.Optional;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getTitle(),productDto.getCost());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(),product.getTitle(),product.getCost());
    }

    public ProductDto entityToDto(Optional<Product> product) {
        return new ProductDto(product.get().getId(), product.get().getTitle(),product.get().getCost());
    }
}
