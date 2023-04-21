package simplicity;

import java.util.ArrayList;

public class Food extends Objek {
    // Atribut

    private ArrayList<Groceries> listGroceries;
    private int foodHunger;

    // Konstruktor

    public Food(String objekName, ArrayList<Groceries> listofGroceries, int foodHunger) {
        super(objekName);
        this.foodHunger = foodHunger;
        this.listGroceries = listofGroceries;
    }

    public Food(String objekName) {
        super(objekName);
    }

    // Method

    public ArrayList<Groceries> getListGroceries() {
        return listGroceries;
    }

    public int getFoodHunger() {
        return foodHunger;
    }

    public void setListGroceries(ArrayList<Groceries> listGroceries) {
        this.listGroceries = listGroceries;
    }

    public void setFoodHunger(int foodHunger) {
        this.foodHunger = foodHunger;
    }
}
