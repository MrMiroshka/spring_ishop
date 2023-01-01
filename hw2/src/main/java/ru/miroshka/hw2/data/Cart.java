package ru.miroshka.hw2.data;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data

public class Cart {
    List<Product> listProducts;

    public Cart() {
        listProducts = new ArrayList<>();
    }

    /**
     * Удаляет первый попавшийся продукт в корзине с заданным id
     */
    public void deleteById(Long id) {
        for (Product p : listProducts) {
            if (p.getId() == id) {
                listProducts.remove(p);
                break;
            }
        }
    }

    /**
     * Удаляет все продукт в корзине с заданным id
     */
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
}
