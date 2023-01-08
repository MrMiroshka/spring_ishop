package ru.miroshka.hw4.models;

import lombok.Data;
import ru.miroshka.hw4.data.Product;

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
        for (CartDtoItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        this.items.add(new CartDtoItem(product.getId(), product.getTitle(), 1,
                product.getCost(), product.getCost()));
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
        items.removeIf(n -> (n.getProductId().equals(id)));
        recalculate();
    }

    /**
     * Удаляет 1 штуку из группы товаров.
     * Если на остатке 1 продукт то удаляю товар в корзине с заданным id
     **/
    public void deleteOneById(Long id) {
        for (CartDtoItem p : items) {
            if (p.getProductId().equals(id)) {
                if (p.getQuantity() == 1) {
                    items.remove(p);
                } else if (p.getQuantity() > 1) {
                    p.changeQuantity(-1);
                }
                recalculate();
                return;
            }
        }
    }

    /**
     * Удаляет все содержимое корзины
     */
    public void deleteByAll() {
        items.clear();
        recalculate();
    }
}
