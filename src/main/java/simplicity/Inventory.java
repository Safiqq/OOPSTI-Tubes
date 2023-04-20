package simplicity;

public class Inventory {
    private Box<NonFood> boxNonFood;
    private Box<Groceries> boxGroceries;
    private Box<Food> boxFood;

    public Inventory() {
        boxNonFood = new Box<NonFood>();
        boxGroceries = new Box<Groceries>();
        boxFood = new Box<Food>();
    }

    public Inventory(Box<NonFood> boxNonFood, Box<Groceries> boxGroceries, Box<Food> boxFood) {
        this.boxNonFood = boxNonFood;
        this.boxGroceries = boxGroceries;
        this.boxFood = boxFood;
    }

    public Box<NonFood> getBoxNonFood() {
        return boxNonFood;
    }

    public Box<Groceries> getBoxGroceries() {
        return boxGroceries;
    }

    public Box<Food> getBoxFood() {
        return boxFood;
    }

    public void setBoxNonFood(Box<NonFood> boxNonFood) {
        this.boxNonFood = boxNonFood;
    }

    public void setBoxGroceries(Box<Groceries> boxGroceries) {
        this.boxGroceries = boxGroceries;
    }

    public void setBoxFood(Box<Food> boxFood) {
        this.boxFood = boxFood;
    }
}
