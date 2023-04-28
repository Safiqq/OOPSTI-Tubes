package simplicity;

public class Inventory {
    private Box<NonFood> boxNonFood;
    private Box<Groceries> boxGroceries;
    private Box<Food> boxFood;

    public Inventory() {
        boxNonFood = new Box<>();
        boxGroceries = new Box<>();
        boxFood = new Box<>();
    }

    public Inventory(Box<NonFood> boxNonFood, Box<Groceries> boxGroceries, Box<Food> boxFood) {
        this.boxNonFood = boxNonFood;
        this.boxGroceries = boxGroceries;
        this.boxFood = boxFood;
    }

    public Box<NonFood> getBoxNonFood() {
        return boxNonFood;
    }

    public void setBoxNonFood(Box<NonFood> boxNonFood) {
        this.boxNonFood = boxNonFood;
    }

    public Box<Groceries> getBoxGroceries() {
        return boxGroceries;
    }

    public void setBoxGroceries(Box<Groceries> boxGroceries) {
        this.boxGroceries = boxGroceries;
    }

    public Box<Food> getBoxFood() {
        return boxFood;
    }

    public void setBoxFood(Box<Food> boxFood) {
        this.boxFood = boxFood;
    }
}
