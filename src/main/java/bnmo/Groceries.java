package bnmo;

public class Groceries extends Objek {
    private int grocPrice;
    private int grocHunger;

    public Groceries(String objekName, int grocPrice, int grocHunger){
        super(objekName);
        this.grocPrice = grocPrice;
        this.grocHunger = grocHunger;
    }

    public int getGrocPrice(){
        return grocPrice;
    }

    public int getGrocHunger(){
        return grocHunger;
    }

    public void setGrocPrice(int grocPrice){
        this.grocPrice = grocPrice;
    }

    public void setGrocHunger(int grocHunger){
        this.grocHunger = grocHunger;
    }
}
