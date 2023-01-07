package ru.miroshka.hw4.converters;

import org.springframework.stereotype.Component;
import ru.miroshka.hw4.data.Order;
import ru.miroshka.hw4.dto.OrderDto;

@Component
public class OrderConverter {
    public OrderDto entityToDto(Order order){
        return new OrderDto(order.getId(),order.getPhone(),order.getTotalPrice(),order.getAddress(),order.getCreatedAt());
    }
/*    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getTitle(),productDto.getCost(), LocalDateTime.now(),LocalDateTime.now());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(),product.getTitle(),product.getCost());
    }

    public ProductDto entityToDto(Optional<Product> product) {
        return new ProductDto(product.get().getId(), product.get().getTitle(),product.get().getCost());
    }*/
}
