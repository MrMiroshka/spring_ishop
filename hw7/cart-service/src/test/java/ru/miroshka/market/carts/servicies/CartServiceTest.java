package ru.miroshka.market.carts.servicies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.miroshka.market.api.dto.ProductDto;
import ru.miroshka.market.carts.models.Cart;
import ru.miroshka.market.carts.models.CartItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void getCurrentCart() {
        Cart cart = cartService.getCurrentCart();

        Assertions.assertNotNull(cart);

        cart.deleteByAll();
        Assertions.assertEquals(cart.getItems().size(),0);

        cart.addListProduct(new ProductDto(222L,"test",new BigDecimal("222")));
        Assertions.assertEquals(cart.getItems().get(cart.getItems().size()-1).getProductTitle()
                ,"test");

    }

    @Test
    void putProductToCart() {
        Cart cart = cartService.getCurrentCart();
        Assertions.assertNotNull(cart);

        cart.deleteByAll();
        Assertions.assertEquals(cart.getItems().size(),0);

        cartService.putProductToCart(1L);
        Assertions.assertEquals(cart.getItems().get(cart.getItems().size()-1).getProductTitle()
                ,"Milk");
    }

    @Test
    void getProductsFromBasket() {
        Cart cart = cartService.getCurrentCart();
        Assertions.assertNotNull(cart);

        cart.deleteByAll();
        Assertions.assertEquals(cart.getItems().size(),0);

        cart.addListProduct(new ProductDto(222L,"test",new BigDecimal("222")));
        cart.addListProduct(new ProductDto(223L,"test2",new BigDecimal("223")));

        List<CartItem> lItems= cartService.getProductsFromBasket();
        Assertions.assertEquals(lItems.get(cart.getItems().size()-2).getProductTitle()
                ,"test");
        Assertions.assertEquals(lItems.get(cart.getItems().size()-1).getProductTitle()
                ,"test2");
    }
}