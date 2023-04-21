package simplicity;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class TimeTest {
    private static Time time1, time2, time3;

    @BeforeClass
    public static void setUp() throws Exception {
        time1 = new Time();
        time2 = new Time(29, 2, 2004, 23, 59, 59);
        time3 = new Time();
    }

    @Test
    public void testTimeConstructor() {
        assertEquals("day not equal", time1.getDay(), 1);
        assertEquals("month not equal", time1.getMonth(), 1);
        assertEquals("year not equal", time1.getYear(), 1);
        assertEquals("hour not equal", time1.getHour(), 0);
        assertEquals("minute not equal", time1.getMinute(), 0);
        assertEquals("second not equal", time1.getSecond(), 0);

        assertEquals("day not equal", time2.getDay(), 29);
        assertEquals("month not equal", time2.getMonth(), 2);
        assertEquals("year not equal", time2.getYear(), 2004);
        assertEquals("hour not equal", time2.getHour(), 23);
        assertEquals("minute not equal", time2.getMinute(), 59);
        assertEquals("second not equal", time2.getSecond(), 59);
    }

    @Test
    public void testTimeSet() {
        time3.setDay(20);
        time3.setMonth(12);
        time3.setYear(2025);
        time3.setHour(1);
        time3.setMinute(1);
        time3.setSecond(1);
        assertEquals("day not equal", time3.getDay(), 20);
        assertEquals("month not equal", time3.getMonth(), 12);
        assertEquals("year not equal", time3.getYear(), 2025);
        assertEquals("hour not equal", time3.getHour(), 1);
        assertEquals("minute not equal", time3.getMinute(), 1);
        assertEquals("second not equal", time3.getSecond(), 1);
    }

    @Test
    public void testTimeThrow() {
        assertThrows(IllegalArgumentException.class, () -> time3.setYear(0));
        assertThrows(IllegalArgumentException.class, () -> time3.setMonth(0));
        assertThrows(IllegalArgumentException.class, () -> time3.setDay(0));
        assertThrows(IllegalArgumentException.class, () -> time3.setHour(24));
        assertThrows(IllegalArgumentException.class, () -> time3.setMinute(60));
        assertThrows(IllegalArgumentException.class, () -> time3.setSecond(60));
        assertThrows(IllegalArgumentException.class, () -> time3.setTime(29, 2, 2003, 0, 0, 0));
    }
}
