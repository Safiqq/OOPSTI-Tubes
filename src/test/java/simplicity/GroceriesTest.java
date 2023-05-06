package simplicity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroceriesTest {
    private static Groceries gr1, gr2;

    @BeforeClass
    public static void setUp() {
        gr1 = new Groceries("Nasi");
        gr2 = new Groceries("Kentang");
    }

    @Test
    public void testGroceries() {
        assertEquals("Nasi", gr1.getObjekName());
        assertEquals("Kentang", gr2.getObjekName());
    }
}
