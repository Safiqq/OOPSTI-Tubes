package simplicity;

import java.util.ArrayList;
import java.util.List;

public class NonFood extends Objek implements Purchasable {
    private static final List<NonFood> listNonFood = new ArrayList<>();
    private int length;
    private int width;
    private int price;
    private List<Action> listAction;
    private Point startPoint;
    private Point endPoint;

    public NonFood(String objekName) {
        this(objekName, null, null);
    }

    public NonFood(String objekName, Point startPoint, Point endPoint) {
        super(objekName);
        listAction = new ArrayList<>();
        listAction.add(Action.get("Work"));
        listAction.add(Action.get("Exercise"));
        if (Main.equals(objekName, "Kasur Single")) {
            setLWP(4, 1, 50);

            listAction.add(Action.get("Sleep"));
            listAction.add(Action.get("Sit"));
        } else if (Main.equals(objekName, "Kasur Queen Size")) {
            setLWP(4, 2, 100);

            listAction.add(Action.get("Sleep"));
            listAction.add(Action.get("Sit"));
        } else if (Main.equals(objekName, "Kasur King Size")) {
            setLWP(5, 2, 150);

            listAction.add(Action.get("Sleep"));
            listAction.add(Action.get("Sit"));
        } else if (Main.equals(objekName, "Toilet")) {
            setLWP(1, 1, 50);

            listAction.add(Action.get("Pee"));
        } else if (Main.equals(objekName, "Kompor Gas")) {
            setLWP(2, 1, 100);

            listAction.add(Action.get("Cook"));
            listAction.add(Action.get("Turn On Stove"));
            listAction.add(Action.get("Turn Off Stove"));
        } else if (Main.equals(objekName, "Kompor Listrik")) {
            setLWP(1, 1, 200);

            listAction.add(Action.get("Cook"));
            listAction.add(Action.get("Turn On Stove"));
            listAction.add(Action.get("Turn Off Stove"));
        } else if (Main.equals(objekName, "Meja dan Kursi")) {
            setLWP(3, 3, 50);

            listAction.add(Action.get("Eat"));
            listAction.add(Action.get("Climb Table and Chair"));
            listAction.add(Action.get("Sit"));
        } else if (Main.equals(objekName, "Jam")) {
            setLWP(1, 1, 10);

            listAction.add(Action.get("Check Time"));
        } else if (Main.equals(objekName, "Wastafel")) {
            setLWP(1, 1, 25);

            listAction.add(Action.get("Wash Hand"));
        } else if (Main.equals(objekName, "Cermin")) {
            setLWP(1, 1, 50);

            listAction.add(Action.get("Look Mirror"));
            listAction.add(Action.get("Dance Mirror"));
        }

        listAction.add(Action.get("Visit"));
        listAction.add(Action.get("Upgrade House"));
        listAction.add(Action.get("Buy Item"));
        listAction.add(Action.get("Move Room"));
        listAction.add(Action.get("View Inventory"));
        listAction.add(Action.get("Install Item"));

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public NonFood(String objekName, Point startPoint) {
        this(objekName, startPoint, startPoint);
    }

    public static List<NonFood> getListNonFood() {
        return listNonFood;
    }

    public static void fillListNonFood() {
        /* Kasur single */
        listNonFood.add(new NonFood("Kasur single"));

        /* Kasur queen size */
        listNonFood.add(new NonFood("Kasur queen size"));

        /* Kasur king size */
        listNonFood.add(new NonFood("Kasur king size"));

        /* Toilet */
        listNonFood.add(new NonFood("toilet"));

        /* Kompor gas */
        listNonFood.add(new NonFood("Kompor gas"));

        /* Kompor listrik */
        listNonFood.add(new NonFood("Kompor listrik"));

        /* Meja dan kursi */
        listNonFood.add(new NonFood("Meja dan kursi"));

        /* Jam */
        listNonFood.add(new NonFood("Jam"));

        /* Objek Buatan */
        /* Wastafel */
        listNonFood.add(new NonFood("Wastafel"));

        /* Cermin */
        listNonFood.add(new NonFood("Cermin"));
    }

    public static NonFood get(String objekName) {
        for (NonFood objek : listNonFood) {
            if (Main.equals(objekName, objek.getObjekName())) {
                return objek;
            }
        }
        return null;
    }

    public void setLWP(int length, int width, int price) {
        setLength(length);
        setWidth(width);
        setPrice(price);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Action> getListAction() {
        return listAction;
    }

    public void setListAction(List<Action> action) {
        this.listAction = action;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
