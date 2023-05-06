package simplicity;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActionTest {
    private static Action a1, a2;

    @BeforeClass
    public static void setUp() {
        a1 = new Action("Work", "Sim harus bekerja");
        a2 = new Action("Exercise", "Sim dapat berolahraga");
    }

    @Test
    public void testFood() {
        assertEquals("Work", a1.getActionName());
        assertEquals("Sim harus bekerja", a2.getDescription());
        assertEquals("Exercise", a2.getActionName());
        assertEquals("Sim dapat berolahraga", a2.getDescription());
    }
}

