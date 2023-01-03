package ru.miroshka.hw2.dto;

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
}
