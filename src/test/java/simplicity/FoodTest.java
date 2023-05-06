package simplicity;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoodTest {
    private static Food f1, f2;

    @BeforeClass
    public static void setUp() {
        f1 = new Food("Nasi Ayam");
        f2 = new Food("Nasi Kari");
    }

    @Test
    public void testFood() {
        assertEquals("Nasi Ayam", f1.getObjekName());
        assertEquals("Nasi Kari", f2.getObjekName());
    }
}

