package simplicity;

import java.util.ArrayList;
import java.util.List;

public class Food extends Objek {
    private static final List<Food> listFood = new ArrayList<>();
    private ArrayList<Groceries> listGroceries;
    private int foodHunger;

    public Food(String objekName, ArrayList<Groceries> listGroceries, int foodHunger) {
        super(objekName);
        this.foodHunger = foodHunger;
        this.listGroceries = listGroceries;
    }

    public Food(String objekName, int foodHunger) {
        this(objekName, new ArrayList<>(), foodHunger);
    }

    public Food(String objekName) {
        super(objekName);
        listGroceries = new ArrayList<>();
        if (Main.equals(objekName, "Nasi Ayam")) {
            foodHunger = 16;

            listGroceries.add(Groceries.get("Nasi"));
            listGroceries.add(Groceries.get("Ayam"));
        } else if (Main.equals(objekName, "Nasi Kari")) {
            foodHunger = 30;

            listGroceries.add(Groceries.get("Nasi"));
            listGroceries.add(Groceries.get("Kentang"));
            listGroceries.add(Groceries.get("Wortel"));
            listGroceries.add(Groceries.get("Sapi"));
        } else if (Main.equals(objekName, "Susu Kacang")) {
            foodHunger = 5;

            listGroceries.add(Groceries.get("Susu"));
            listGroceries.add(Groceries.get("Kacang"));
        } else if (Main.equals(objekName, "Tumis Sayur")) {
            foodHunger = 5;

            listGroceries.add(Groceries.get("Wortel"));
            listGroceries.add(Groceries.get("Bayam"));
        } else if (Main.equals(objekName, "Bistik")) {
            foodHunger = 22;

            listGroceries.add(Groceries.get("Kentang"));
            listGroceries.add(Groceries.get("Sapi"));
        }
    }

    public static void fillListFood() {
        /* Nasi Ayam */
        listFood.add(new Food("Nasi Ayam"));

        /* Nasi Kari */
        listFood.add(new Food("Nasi Kari"));

        /* Susu Kacang */
        listFood.add(new Food("Susu Kacang"));

        /* Tumis Sayur */
        listFood.add(new Food("Tumis Sayur"));

        /* Bistik */
        listFood.add(new Food("Bistik"));
    }

    public static Food get(String objekName) {
        for (Food food : listFood) {
            if (Main.equals(objekName, food.getObjekName())) {
                return food;
            }
        }
        return null;
    }

    public static List<Food> getListFood() {
        return listFood;
    }

    public ArrayList<Groceries> getListGroceries() {
        return listGroceries;
    }

    public void setListGroceries(ArrayList<Groceries> listGroceries) {
        this.listGroceries = listGroceries;
    }

    public int getFoodHunger() {
        return foodHunger;
    }

    public void setFoodHunger(int foodHunger) {
        this.foodHunger = foodHunger;
    }
}
