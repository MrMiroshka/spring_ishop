package ru.miroshka.hw3.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.miroshka.hw3.data.Order;
import ru.miroshka.hw3.data.OrderItem;
import ru.miroshka.hw3.data.User;
import ru.miroshka.hw3.models.CartDto;
import ru.miroshka.hw3.models.CartDtoItem;
import ru.miroshka.hw3.repositories.OrderDao;
import ru.miroshka.hw3.repositories.OrderItemsDao;
import ru.miroshka.hw3.repositories.ProductDao;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderDao orderDao;
    private final OrderItemsDao orderItemsDao;

    @Transactional
    public void createOrder(User user){
        CartDto cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        orderDao.save(order);
        for(CartDtoItem cartDtoItem:cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productService.findById(cartDtoItem.getProductId()).get());
            orderItem.setOrder(order);
            orderItem.setQuantity(cartDtoItem.getQuantity());
            orderItem.setPricePerProduct(cartDtoItem.getPricePerProduct());
            orderItem.setPrice(cartDtoItem.getPrice());
            orderItemsDao.save(orderItem);
        }

        cart.deleteByAll();
    }
}
