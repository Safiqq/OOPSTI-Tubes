package simplicity;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class MotiveTest {
    private static Motive motive;

    @BeforeClass
    public static void setUp() throws Exception {
        motive = new Motive();
    }

    @Test
    public void testMotiveConstructor(){
        assertEquals("mood not equal", motive.getMood(), 100);
        assertEquals("hunger not equal", motive.getHunger(), 100);
        assertEquals("health not equal", motive.getHealth(), 100);
    }

    @Test
    public void testMotiveTest(){
        motive.changeMood(-20);
        motive.changeHunger(30);
        assertEquals("mood not equal", motive.getMood(), 80);
        assertEquals("hunger not equal", motive.getHunger(), 100);
        assertThrows(IllegalArgumentException.class, () -> motive.changeHealth(-120));

        motive.setMood(30);
        motive.setHealth(10);
        assertEquals("mood not equal", motive.getMood(), 30);
        assertThrows(IllegalArgumentException.class, () -> motive.setHunger(-10));
        assertEquals("health not equal", motive.getHealth(), 10);
    }
}
