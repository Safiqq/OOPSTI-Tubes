package simplicity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NonFoodTest {
    private static NonFood nf1, nf2;

    @BeforeClass
    public static void setUp() {
        nf1 = new NonFood("Kasur Single");
        nf2 = new NonFood("Kasur Queen Size");
    }

    @Test
    public void testNonFood() {
        assertEquals("Kasur Single", nf1.getObjekName());
        assertEquals("Kasur Queen Size", nf2.getObjekName());
    }
}
