package bnmo;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class TimeTest {
    private static Time time;

    @BeforeClass
    public static void setUp() throws Exception {
        time1 = new Time();
        time2 = new Time(29, 2, 2004, 23, 59, 59);
    }

    @Test
    public void testTimeConstrucor(){
        assertEquals("day not equal", time1.getDay(), 19);
        assertEquals("month not equal", time1.getMonth(), 4);
        assertEquals("year not equal", time1.getYear(), 2023);
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
    public void testTimeSet(){
        time1.setDay(20);
        time1.setMonth(12);
        time1.setYear(2025);
        time1.setHour(1);
        time1.setMinute(1);
        time1.setSecond(1);
        assertEquals("day not equal", time1.getDay(), 20);
        assertEquals("month not equal", time1.getMonth(), 12);
        assertEquals("year not equal", time1.getYear(), 2025);
        assertEquals("hour not equal", time1.getHour(), 1);
        assertEquals("minute not equal", time1.getMinute(), 1);
        assertEquals("second not equal", time1.getSecond(), 1);

        time2.setTime(29, 2, 2003, 0, 0, 0); // exception        
    }
}
