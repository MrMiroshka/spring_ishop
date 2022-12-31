package ru.miroshka.market.servicies;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.miroshka.market.data.Cart;
import ru.miroshka.market.data.Product;
import ru.miroshka.market.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class CartService {
    private final Cart productCart;

    private final ProductService productService;

    public CartService(Cart productCart,@Lazy ProductService productService) {
        this.productCart = productCart;
        this.productService = productService;
    }


    public void delProductBasketById(Long id) {
        this.productCart.deleteById(id);
    }

    public List<Product> putProductToCart(Long id){
        Product product = this.productService.findById(id).get();
        Product productPutToBasket = this.productService.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Такой продукт не найден id - " + product.getId()));
        productCart.getListProducts().add(productPutToBasket);
        return productCart.getListProducts();
    }

    public List<Product> getProductsFromBasket(){
        return productCart.getListProducts();
    }


    public void deleteByIdAll(Long id) {
        this.productCart.deleteByIdAll(id);
    }
}
