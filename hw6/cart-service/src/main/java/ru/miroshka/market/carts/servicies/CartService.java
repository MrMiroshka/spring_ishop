package ru.miroshka.market.carts.servicies;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.miroshka.market.api.dto.ProductDto;
import ru.miroshka.market.api.exceptions.ResourceNotFoundException;
import ru.miroshka.market.carts.integrations.ProductServiceIntegration;
import ru.miroshka.market.carts.models.Cart;
import ru.miroshka.market.carts.models.CartItem;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CartService {
    private Cart productCart;
    private final ProductServiceIntegration productServiceIntegration;

    public Cart getCurrentCart() {
        return productCart;
    }

    public CartService(@Lazy ProductServiceIntegration productServiceIntegration) {
        this.productServiceIntegration = productServiceIntegration;
    }

    @PostConstruct
    public void init() {
        productCart = new Cart();
    }


    public void delProductCartById(Long id) {
        this.productCart.deleteById(id);
    }

    public void delProductCartOneById(Long id) {
        this.productCart.deleteOneById(id);
    }

    public void delAllProductBasketById() {
        this.productCart.deleteByAll();
    }

    public List<CartItem> putProductToCart(Long id) {
        ProductDto productPutToBasket = this.productServiceIntegration.getProductById(id).orElseThrow(() ->
                new ResourceNotFoundException("Такой продукт не найден id - " + id
                        + "! Не удается добавить продукт в корзину!"));
        productCart.addListProduct(productPutToBasket);
        return productCart.getItems();
    }


    public List<CartItem> getProductsFromBasket() {
        return productCart.getItems();
    }

}
