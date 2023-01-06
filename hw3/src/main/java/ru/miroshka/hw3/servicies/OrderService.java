package ru.miroshka.hw3.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.miroshka.hw3.data.Order;
import ru.miroshka.hw3.data.User;
import ru.miroshka.hw3.models.CartDto;
import ru.miroshka.hw3.models.CartDtoItem;
import ru.miroshka.hw3.repositories.OrderDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderDao orderDao;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(User user){
        CartDto cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        orderDao.save(order);
        for(CartDtoItem cartDtoItem:cart.getItems()){
            orderItemService.createOrderItem(cartDtoItem,order);
        }

        cart.deleteByAll();
    }

    public List<Order> selectOrders(User user) {
        return orderDao.findByUser(user);
    }
}
