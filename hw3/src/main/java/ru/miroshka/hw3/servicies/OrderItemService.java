package ru.miroshka.hw3.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.miroshka.hw3.data.Order;
import ru.miroshka.hw3.data.OrderItem;
import ru.miroshka.hw3.data.User;
import ru.miroshka.hw3.models.CartDtoItem;
import ru.miroshka.hw3.repositories.OrderItemsDao;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemsDao orderItemsDao;
    private final ProductService productService;

    public void createOrderItem(CartDtoItem cartDtoItem, Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productService.findById(cartDtoItem.getProductId()).get());
        orderItem.setOrder(order);
        orderItem.setQuantity(cartDtoItem.getQuantity());
        orderItem.setPricePerProduct(cartDtoItem.getPricePerProduct());
        orderItem.setPrice(cartDtoItem.getPrice());
        orderItemsDao.save(orderItem);
    }
}
