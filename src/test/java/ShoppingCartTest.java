
import org.example.Item;
import org.example.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    @Test
    void totalValueShouldSumAllItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(new Item("Milk", 2.0, 3));   // 6
        cart.addToCart(new Item("Bread", 1.5, 2));  // 3

        assertEquals(9.0, cart.totalValue(), 0.0001);
    }

    @Test
    void emptyCartShouldHaveZeroTotal() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0.0, cart.totalValue(), 0.0001);
    }

    @Test
    void itemCountShouldReturnNumberOfDistinctItems() {
        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(new Item("Milk", 2.0, 1));
        cart.addToCart(new Item("Bread", 1.0, 2));

        assertEquals(2, cart.itemCount());
    }
}