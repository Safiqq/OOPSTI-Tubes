package bnmo;

import java.util.ArrayList;

public class Food extends Objek{
    private ArrayList<Groceries> listGroceries;
    private int foodHunger;

    public Food(String objekName, ArrayList<Groceries> listofGroceries, int foodHunger){
        super(objekName);
        this.foodHunger = foodHunger;
        this.listGroceries = listofGroceries;
    }

    public Food(String objekName){
        super(objekName);
    }

    public ArrayList<Groceries> getListGroceries(){
        return listGroceries;
    }

    public int getFoodHunger(){
        return foodHunger;
    }

    public void setListGroceries(ArrayList<Groceries> listGroceries){
        this.listGroceries = listGroceries;
    }

    public void setFoodHunger(int foodHunger){
        this.foodHunger = foodHunger;
    }
}
