

import org.example.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Test
    void totalPriceShouldBePriceTimesQuantity() {
        Item item = new Item("Milk", 2.5, 4);
        assertEquals(10.0, item.totalPrice(), 0.0001);
    }

}