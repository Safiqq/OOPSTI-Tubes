package simplicity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjekTest {
    private static Objek obj;

    @BeforeClass
    public static void setUp() {
        obj = new Objek("Kursi");
    }

    @Test
    public void testObjekName() {
        assertEquals("Kursi", obj.getObjekName());
        obj.setObjekName("Meja");
        assertEquals("Meja", obj.getObjekName());
    }
}
