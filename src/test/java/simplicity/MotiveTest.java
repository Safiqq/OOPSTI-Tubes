package simplicity;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class MotiveTest {
    private static Motive motive1, motive2;

    @BeforeClass
    public static void setUp() throws Exception {
        motive1 = new Motive();
        motive2 = new Motive();
    }

    @Test
    public void testMotiveConstructor() {
        assertEquals("mood not equal", motive1.getMood(), 80);
        assertEquals("hunger not equal", motive1.getHunger(), 80);
        assertEquals("health not equal", motive1.getHealth(), 80);
    }

    @Test
    public void testMotiveTest() {
        motive2.changeMood(-20);
        motive2.changeHunger(30);
        assertEquals("mood not equal", motive2.getMood(), 60);
        assertEquals("hunger not equal", motive2.getHunger(), 100);
        assertThrows(IllegalArgumentException.class, () -> motive2.changeHealth(-120));

        motive2.setMood(30);
        motive2.setHealth(10);
        assertEquals("mood not equal", motive2.getMood(), 30);
        assertThrows(IllegalArgumentException.class, () -> motive2.setHunger(-10));
        assertEquals("health not equal", motive2.getHealth(), 10);
    }
}
