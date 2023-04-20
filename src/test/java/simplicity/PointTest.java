package simplicity;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class PointTest {
    private static Point p1, p2;

    @BeforeClass
    public static void setUp() throws Exception {
        p1 = new Point();
        p2 = new Point(5, 3);
    }

    @Test
    public void testPointConstructor() {
        assertEquals("x not equal", 0, p1.getX());
        assertEquals("y not equal", 0, p1.getY());

        assertEquals("x not equal", 5, p2.getX());
        assertEquals("y not equal", 3, p2.getY());
    }

    @Test
    public void testPointSet() {
        p1.setX(3);
        p1.setY(5);
        assertEquals("x not equal", 3, p1.getX());
        assertEquals("y not equal", 5, p1.getY());
    }
}
