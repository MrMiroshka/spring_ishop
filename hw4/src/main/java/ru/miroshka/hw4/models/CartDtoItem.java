package ru.miroshka.hw4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDtoItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public void changeQuantity(int delta) {
        quantity += delta;
        price =  pricePerProduct * quantity;
    }
}
