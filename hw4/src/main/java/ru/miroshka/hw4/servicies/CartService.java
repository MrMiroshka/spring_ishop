package ru.miroshka.hw4.servicies;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.miroshka.hw4.data.Product;
import ru.miroshka.hw4.exceptions.ResourceNotFoundException;
import ru.miroshka.hw4.models.CartDto;
import ru.miroshka.hw4.models.CartDtoItem;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CartService {
    private  CartDto productCartDto;
    private final ProductService productService;

    public CartDto getCurrentCart(){
        return productCartDto;
    }

    public CartService(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init(){
        productCartDto = new CartDto();
    }


    public void delProductCartById(Long id) {
        this.productCartDto.deleteById(id);
    }

    public void delProductCartOneById(Long id){
        this.productCartDto.deleteOneById(id);
    }

    public void delAllProductBasketById() {
        this.productCartDto.deleteByAll();
    }

    public List<CartDtoItem> putProductToCart(Long id){
       // Product product = this.productService.findById(id).get();
        Product productPutToBasket = this.productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Такой продукт не найден id - " + id
                        + "! Не удается добавить продукт в корзину!"));

        productCartDto.addListProduct(productPutToBasket);
        return productCartDto.getItems();
    }


    public List<CartDtoItem> getProductsFromBasket(){
        return productCartDto.getItems();
    }

}
