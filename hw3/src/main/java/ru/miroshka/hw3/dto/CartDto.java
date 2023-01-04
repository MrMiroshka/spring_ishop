package ru.miroshka.hw3.dto;

import lombok.Data;

import ru.miroshka.hw3.data.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Component
@Data
public class CartDto {
    private List<CartDtoItem> items;
    private int totalPrice;

    public CartDto() {
        this.items = new ArrayList<>();
    }

    public List<CartDtoItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addListProduct(Product product) {
        boolean productInCart = false;
        for (CartDtoItem item : items) {
            if (item.getProductId() == product.getId()) {
                productInCart = true;
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                break;
            }
            ;
        }
        if (!productInCart) {
            this.items.add(new CartDtoItem(product.getId(), product.getTitle(), 1,
                    product.getCost(), product.getCost()));
        }

        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartDtoItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    /**
     * Удаляет первый попавшийся продукт в корзине с заданным id
     **/
    public void deleteById(Long id) {
        for (CartDtoItem p : items) {
            if (p.getProductId() == id) {
                items.remove(p);
                break;
            }
        }
        recalculate();
    }

    /**
     * Удаляет 1 штуку из группы товаров.
     * Если на остатке 1 продукт то удаляю товар в корзине с заданным id
     **/
    public void deleteOneById(Long id) {
        for (CartDtoItem p : items) {
            if (p.getProductId() == id) {
                if (p.getQuantity() == 1) {
                    items.remove(p);
                } else if (p.getQuantity() > 1) {
                    p.setQuantity(p.getQuantity() - 1);
                    p.setPrice(p.getPricePerProduct() * p.getQuantity());
                }
                break;
            }
        }
        recalculate();
    }

    /**
     * Удаляет все содержимое корзины
     */
    public void deleteByAll() {
        items.clear();
        recalculate();
    }


    /**
     * Удаляет все продукт в корзине с заданным id
     */
    public void deleteByIdAll(Long id) {
        List<CartDtoItem> listDelete = new ArrayList<>();
        for (CartDtoItem p : items) {
            if (p.getProductId() == id) {
                listDelete.add(p);
            }
        }

        for (CartDtoItem p : listDelete) {
            items.remove(p);
        }
        recalculate();
    }

    /*List<Product> listProducts;

    public CartDto() {
        listProducts = new ArrayList<>();
    }

    public List<Product> getListProducts() {
        return Collections.unmodifiableList(this.listProducts);
    }


    public void addListProduct(Product product) {
        this.listProducts.add(product);
    }


    *//**
     * Удаляет первый попавшийся продукт в корзине с заданным id
     *//*
    public void deleteById(Long id) {
        for (Product p : listProducts) {
            if (p.getId() == id) {
                listProducts.remove(p);
                break;
            }
        }
    }

    *//**
     * Удаляет все продукт в корзине с заданным id
     *//*
    public void deleteByIdAll(Long id) {
        List<Product> listDelete = new ArrayList<>();
        for (Product p : listProducts) {
            if (p.getId() == id) {
                listDelete.add(p);
            }
        }

        for (Product p : listDelete) {
            listProducts.remove(p);
        }
    }

    *//**
     * Удаляет все содержимое корзины
     *//*
    public void deleteByAll() {
        listProducts.clear();
    }*/
}
