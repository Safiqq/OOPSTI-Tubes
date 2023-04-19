package bnmo;

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
        motive.minMood(20);
        motive.minHunger(30);
        motive.minHealth(50);
        assertEquals("mood not equal", motive.getMood(), 80);
        assertEquals("hunger not equal", motive.getHunger(), 70);
        assertEquals("health not equal", motive.getHealth(), 50);

        motive.addMood(30);
        motive.addHunger(20);
        motive.addHealth(10);
        assertEquals("mood not equal", motive.getMood(), 100);
        assertEquals("hunger not equal", motive.getHunger(), 90);
        assertEquals("health not equal", motive.getHealth(), 60);

        motive.setMood(120);
        motive.setHunger(50);
        motive.setHealth(70);
        assertEquals("mood not equal", motive.getMood(), 100);
        assertEquals("hunger not equal", motive.getHunger(), 50);
        assertEquals("health not equal", motive.getHealth(), 70);
    }
}
