package simplicity;


import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class FoodTest {
    private static Food f,f1;

    @BeforeClass
    public static void setUp() throws Exception {
        f = new Food("Nasi");
        f1 = new Food("Ikan",new ArrayList<Groceries>(),50);
    }

    @Test
    public void testFood(){
        assertEquals("Nasi",f.getObjekName());
        f.setObjekName("Ayam Goreng");
        assertEquals("Ayam Goreng",f.getObjekName());

        assertEquals("Ikan",f1.getObjekName());

        ArrayList<Groceries> listbelanja2 = new ArrayList<Groceries>();
        Groceries g1 = new Groceries("Ikan");
        listbelanja2.add(g1);

        for(int i = 0; i < listbelanja2.size(); i ++ ){
            if(f1.getListGroceries().contains(listbelanja2.get(i))){
                assertEquals("sama","sama");
            }
        }

        // assertEquals(listbelanja2,f1.getListGroceries());


        assertEquals(50,f1.getFoodHunger());

        ArrayList<Groceries> listbelanja3 = new ArrayList<Groceries>();
        Groceries g2 = new Groceries("Sapi");
        Groceries g3 = new Groceries("Apel");

        listbelanja3.add(g2);
        listbelanja3.add(g3);

        f1.setListGroceries(listbelanja3);
        f1.setFoodHunger(30);

        ArrayList<Groceries> listbelanjachecker = new ArrayList<Groceries>();
        listbelanjachecker.add(g2);
        listbelanjachecker.add(g3);

        assertEquals(listbelanjachecker,f1.getListGroceries());
        assertEquals(30,f1.getFoodHunger());
    }
}

