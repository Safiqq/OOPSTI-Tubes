package simplicity;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class ObjekTest {
    private static Objek obj1;

    @BeforeClass
    public static void setUp() throws Exception {
        obj1 = new Objek("Kursi");
    }

    @Test
    public void testObjekName(){
        assertEquals("Kursi",obj1.getObjekName());
        obj1.setObjekName("Meja");
        assertEquals("Meja",obj1.getObjekName());
    }
}
