package simplicity;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class NonFoodTest {
    private static NonFood nf,nf1;

    @BeforeClass
    public static void setUp() throws Exception {
        nf = new NonFood("Kursi Gemink");
        nf1 = new NonFood("Kasur",2,1,1000000,new Action("Ditiduri","Ya ditiduri"),new Point(0,0), new Point(2,2));
    }

    @Test
    public void testNonFood(){
        assertEquals("Kursi Gemink",nf.getObjekName());
        nf.setObjekName("Meja Gemink");
        assertEquals("Meja Gemink",nf.getObjekName());

        assertEquals("Kasur",nf1.getObjekName());
        assertEquals(2,nf1.getObjLength());
        assertEquals(1,nf1.getObjWidth());
        assertEquals(1000000,nf1.getObjPrice());

        Action act1 = new Action("Ditiduri","Ya ditiduri");
        assertEquals(act1.getActionName(),nf1.getAction().getActionName());
        assertEquals(act1.getDescription(),nf1.getAction().getDescription());
        Point p1 = new Point(0,0);
        Point p2 = new Point(2,2);
        assertEquals(p1.getX(),nf1.getStartPoint().getX());
        assertEquals(p1.getY(),nf1.getStartPoint().getY());
        assertEquals(p2.getX(),nf1.getEndPoint().getX());
        assertEquals(p2.getY(),nf1.getEndPoint().getY());

        nf1.setObjLength(4);
        nf1.setObjWidth(3);
        nf1.setObjPrice(2000000);
        nf1.setAction(new Action("Ditendang","Ya Ditendang"));
        nf1.setStartPoint(new Point(5,5));
        nf1.setEndPoint(new Point(3,3));
        assertEquals(4,nf1.getObjLength());
        assertEquals(3,nf1.getObjWidth());
        assertEquals(2000000,nf1.getObjPrice());
        Action act2 = new Action("Ditendang","Ya Ditendang");
        assertEquals(act2.getActionName(),nf1.getAction().getActionName());
        assertEquals(act2.getDescription(),nf1.getAction().getDescription());
        Point p3 = new Point(5,5);
        Point p4 = new Point(3,3);
        assertEquals(p3.getX(),nf1.getStartPoint().getX());
        assertEquals(p3.getY(),nf1.getStartPoint().getY());
        assertEquals(p4.getX(),nf1.getEndPoint().getX());
        assertEquals(p4.getY(),nf1.getEndPoint().getY());
    }
}
