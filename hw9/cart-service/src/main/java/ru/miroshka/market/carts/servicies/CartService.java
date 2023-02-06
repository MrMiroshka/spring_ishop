package ru.miroshka.market.carts.servicies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.miroshka.market.api.dto.ProductDto;
import ru.miroshka.market.api.dto.StringResponse;
import ru.miroshka.market.api.exceptions.ResourceNotFoundException;
import ru.miroshka.market.carts.integrations.ProductServiceIntegration;
import ru.miroshka.market.carts.models.Cart;
import ru.miroshka.market.carts.models.CartItem;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CartService {
    private Map<String,Cart> productCarts;
    private final ProductServiceIntegration productServiceIntegration;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!productCarts.containsKey(targetUuid)){
            productCarts.put(targetUuid,new Cart());
        }
        return productCarts.get(targetUuid);
    }

    public CartService(@Lazy ProductServiceIntegration productServiceIntegration) {
        this.productServiceIntegration = productServiceIntegration;
    }

    @PostConstruct
    public void init() {
        productCarts = new HashMap<>();
    }


    public void delProductCartById(String uuid,Long id) {
        getCurrentCart(uuid).deleteById(id);
    }

    public void delProductCartOneById(String uuid,Long id) {
        getCurrentCart(uuid).deleteOneById(id);
    }

    public void delAllProductBasketById(String uuid) {
        getCurrentCart(uuid).deleteByAll();
    }

    public List<CartItem> putProductToCart(String uuid,Long id) {
        ProductDto productPutToBasket = this.productServiceIntegration.getProductById(id).orElseThrow(() ->
                new ResourceNotFoundException("Такой продукт не найден id - " + id
                        + "! Не удается добавить продукт в корзину!"));
        getCurrentCart(uuid).addListProduct(productPutToBasket);
        return (getCurrentCart(uuid)).getItems();
    }


    public List<CartItem> getProductsFromBasket(String uuid) {
        return productCarts.get(uuid).getItems();
    }

}
