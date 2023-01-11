package ru.miroshka.market.core.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.miroshka.market.api.models.CartDto;
import ru.miroshka.market.core.data.Order;
import ru.miroshka.market.core.data.OrderItem;
import ru.miroshka.market.core.data.User;
/*import ru.miroshka.market.core.models.CartDto;
import ru.miroshka.market.core.models.CartDtoItem;*/
import ru.miroshka.market.core.repositories.OrderDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
  /*  private final CartService cartService;*/
    private final OrderDao orderDao;
    private final OrderItemService orderItemService;
    //добавил
    private final ProductService productService;


    @Transactional
    public void createOrder(User user) {
      CartDto cartDto = null;// TODO получить корзину из карт МС - cartServiceIntegration.getCurrentCart();
      Order order = new Order();
      order.setUser(user);
      order.setTotalPrice(cartDto.getTotalPrice());
      order.setItems(cartDto.getItems().stream().map(
              cartDtoItem -> new OrderItem(
                      productService.findById(cartDtoItem.getProductId()).get(),
                      order,
                      cartDtoItem.getQuantity(),
                      cartDtoItem.getPricePerProduct(),
                      cartDtoItem.getPrice()
              )
      ).collect(Collectors.toList()));
      orderDao.save(order);

      //TODO cartServiceIntegration.clear();

/*        CartDto cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        orderDao.save(order);
        for (CartDtoItem cartDtoItem : cart.getItems()) {
            orderItemService.createOrderItem(cartDtoItem, order);
        }

        cart.deleteByAll();*/
    }

    public List<Order> selectOrders(User user) {
        return orderDao.findByUser(user);
    }
}
