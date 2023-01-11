package ru.miroshka.market.api.models;

import java.util.List;


public class CartDto {
    private List<CartDtoItem> items;
    private int totalPrice;

    public List<CartDtoItem> getItems() {
        return items;
    }

    public void setItems(List<CartDtoItem> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
