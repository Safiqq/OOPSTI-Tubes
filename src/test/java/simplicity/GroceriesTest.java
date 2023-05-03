package simplicity;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class GroceriesTest {
    private static Groceries gr, gr1;

    @BeforeClass
    public static void setUp() throws Exception {
        gr = new Groceries("Beras");
        gr1 = new Groceries("Roti", 5000, 20);
    }

    @Test
    public void testGroceries() {
        assertEquals("Beras", gr.getObjekName());
        gr.setObjekName("Telur");
        assertEquals("Telur", gr.getObjekName());

        assertEquals("Roti", gr1.getObjekName());
        assertEquals(5000, gr1.getPrice());
        assertEquals(20, gr1.getHunger());
        gr1.setPrice(10000);
        gr1.setHunger(30);
        assertEquals(10000, gr1.getPrice());
        assertEquals(30, gr1.getHunger());
    }
}
