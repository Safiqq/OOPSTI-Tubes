package simplicity;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class TimeTest {
    private static Time time1, time2, time3;

    @BeforeClass
    public static void setUp() throws Exception {
        time1 = new Time();
        time2 = new Time(3, 10, 59);
        time3 = new Time();
    }

    @Test
    public void testTimeConstructor() {
        assertEquals("day not equal", time1.getDay(), 1);
        assertEquals("minute not equal", time1.getMinute(), 11);

        assertEquals("day not equal", time2.getDay(), 3);
        assertEquals("minute not equal", time2.getMinute(), 10);
    }

    @Test
    public void testTimeSet() {
        time3.setDay(20);
        time3.setMinute(1);
        assertEquals("day not equal", time3.getDay(), 20);
        assertEquals("minute not equal", time3.getMinute(), 1);
    }

    @Test
    public void testTimeThrow() {
        assertThrows(IllegalArgumentException.class, () -> time3.setDay(0));
        assertThrows(IllegalArgumentException.class, () -> time3.setSecond(60));
        assertThrows(IllegalArgumentException.class, () -> time3.setTime(0, 12, 1));
    }
}
