import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    // Тестови за секоја наредба (Every Statement)
    @Test
    void testEveryStatement() {
        // allItems == null
        RuntimeException ex1 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, "1234567812345678"));
        assertTrue(ex1.getMessage().contains("allItems list can't be null"));

        // item.getName() == null
        Item item2 = new Item(null, 1, 100, 0);
        RuntimeException ex2 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(List.of(item2), "1234567812345678"));
        assertTrue(ex2.getMessage().contains("Invalid item"));

        // item со попуст и цена > 300 → -30 + попуст
        Item item3 = new Item("Phone", 1, 400, 0.25); // 400 * 0.75 = 300
        assertEquals(270.0, SILab2.checkCart(List.of(item3), "1234567812345678"));

        // валиден item без попуст
        Item item5 = new Item("Chips", 2, 50, 0); // 100
        assertEquals(100, SILab2.checkCart(List.of(item5), "1234567812345678"));
    }

    // Тестови за Multiple Condition: (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
    @Test
    void testMultipleCondition() {
        String card = "1111222233334444";

        // F || F || F
        Item i1 = new Item("i1", 1, 100, 0);
        assertEquals(100, SILab2.checkCart(List.of(i1), card));

        // T || F || F
        Item i2 = new Item("i2", 1, 400, 0);
        assertEquals(370.0, SILab2.checkCart(List.of(i2), card));

        // F || T || F
        Item i3 = new Item("i3", 1, 200, 0.2);
        assertEquals(130.0, SILab2.checkCart(List.of(i3), card));

        // F || F || T
        Item i4 = new Item("i4", 11, 10, 0);
        assertEquals(80.0, SILab2.checkCart(List.of(i4), card));
    }
}
