package ru.miroshka.hw2.servicies;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.miroshka.hw2.data.Cart;
import ru.miroshka.hw2.data.Product;
import ru.miroshka.hw2.exceptions.ResourceNotFoundException;

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

    public void delAllProductBasketById() {
        this.productCart.deleteByAll();
    }

    public List<Product> putProductToCart(Long id){
        Product product = this.productService.findById(id).get();
        Product productPutToBasket = this.productService.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Такой продукт не найден id - " + product.getId()));
        productCart.addListProduct(productPutToBasket);
        return productCart.getListProducts();
    }

    public List<Product> getProductsFromBasket(){
        return productCart.getListProducts();
    }


    public void deleteByIdAll(Long id) {
        this.productCart.deleteByIdAll(id);
    }
}
