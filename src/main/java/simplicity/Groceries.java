package simplicity;

import java.util.ArrayList;
import java.util.List;

public class Groceries extends Objek {
    private static final List<Groceries> listGroceries = new ArrayList<>();
    private int price;
    private int hunger;

    public Groceries(String objekName, int price, int hunger) {
        super(objekName);
        this.price = price;
        this.hunger = hunger;
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

    public void setPH(int price, int hunger) {
        this.price = price;
        this.hunger = hunger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
}
