package simplicity;

import java.util.ArrayList;
import java.util.List;

public class Groceries extends Objek {
    private static final List<Groceries> listGroceries = new ArrayList<>();
    private int grocPrice;
    private int grocHunger;

    public Groceries(String objekName, int grocPrice, int grocHunger) {
        super(objekName);
        this.grocPrice = grocPrice;
        this.grocHunger = grocHunger;
    }

    public Groceries(String objekName) {
        super(objekName);
        if (Main.equals(objekName, "Nasi")) {
            setPH(5, 5);
        } else if (Main.equals(objekName, "Kentang")) {
            setPH(3, 4);
        } else if (Main.equals(objekName, "Ayam")) {
            setPH(10, 8);
        } else if (Main.equals(objekName, "Sapi")) {
            setPH(12, 15);
        } else if (Main.equals(objekName, "Wortel")) {
            setPH(3, 2);
        } else if (Main.equals(objekName, "Bayam")) {
            setPH(3, 2);
        } else if (Main.equals(objekName, "Kacang")) {
            setPH(2, 2);
        } else if (Main.equals(objekName, "Susu")) {
            setPH(2, 1);
        }
    }

    public static List<Groceries> getListGroceries() {
        return listGroceries;
    }

    public static Groceries get(String objekName) {
        for (Groceries groceries : listGroceries) {
            if (Main.equals(objekName, groceries.getObjekName())) {
                return groceries;
            }
        }
        return null;
    }

    public static void fillListGroceries() {
        /* Nasi */
        listGroceries.add(new Groceries("Nasi"));

        /* Kentang */
        listGroceries.add(new Groceries("Kentang"));

        /* Ayam */
        listGroceries.add(new Groceries("Ayam"));

        /* Sapi */
        listGroceries.add(new Groceries("Sapi"));

        /* Wortel */
        listGroceries.add(new Groceries("Wortel"));

        /* Bayam */
        listGroceries.add(new Groceries("Bayam"));

        /* Kacang */
        listGroceries.add(new Groceries("Kacang"));

        /* Susu */
        listGroceries.add(new Groceries("Susu"));
    }

    public void setPH(int grocPrice, int grocHunger) {
        this.grocPrice = grocPrice;
        this.grocHunger = grocHunger;
    }

    public int getGrocPrice() {
        return grocPrice;
    }

    public void setGrocPrice(int grocPrice) {
        this.grocPrice = grocPrice;
    }

    public int getGrocHunger() {
        return grocHunger;
    }

    public void setGrocHunger(int grocHunger) {
        this.grocHunger = grocHunger;
    }
}
