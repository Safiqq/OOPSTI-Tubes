package simplicity;

public class NonFood extends Objek {
    // Atribut

    private int objLength;
    private int objWidth;
    private int objPrice;
    private Action action;
    private Point startPoint;
    private Point endPoint;

    // Konstruktor

    public NonFood(String objekName, int objLength, int objWidth, int objPrice, Action action, Point startPoint, Point endPoint) {
        super(objekName);
        this.objLength = objLength;
        this.objWidth = objWidth;
        this.objPrice = objPrice;
        this.action = action;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public NonFood(String objekName) {
        this(objekName, null, null);
    }

    public NonFood(String objekName, int objLength, int objWidth, int objPrice) {
        super(objekName);
        this.objLength = objLength;
        this.objWidth = objWidth;
        this.objPrice = objPrice;
    }

    public NonFood(String objekName, Action action) {
        super(objekName);
        this.action = action;
    }

    public NonFood(String objekName, Point startPoint, Point endPoint) {
        super(objekName);
        if (Main.equals(objekName, "Kasur single")) {
            objLength = 4;
            objWidth = 1;
            objPrice = 50;
            action = Action.get("Tidur");
        } else if (Main.equals(objekName, "Kasur queen size")) {
            objLength = 4;
            objWidth = 2;
            objPrice = 100;
            action = Action.get("Tidur");
        } else if (Main.equals(objekName, "Kasur king size")) {
            objLength = 5;
            objWidth = 2;
            objPrice = 150;
            action = Action.get("Tidur");
        } else if (Main.equals(objekName, "Toilet")) {
            objLength = 1;
            objWidth = 1;
            objPrice = 50;
            action = Action.get("Buang air");
        } else if (Main.equals(objekName, "Kompor gas")) {
            objLength = 2;
            objWidth = 1;
            objPrice = 100;
            action = Action.get("Memasak");
        } else if (Main.equals(objekName, "Kompor listrik")) {
            objLength = 1;
            objWidth = 1;
            objPrice = 200;
            action = Action.get("Memasak");
        } else if (Main.equals(objekName, "Meja dan kursi")) {
            objLength = 3;
            objWidth = 3;
            objPrice = 50;
            action = Action.get("Makan");
        } else if (Main.equals(objekName, "Jam")) {
            objLength = 1;
            objWidth = 1;
            objPrice = 10;
            action = Action.get("Melihat waktu");
        }
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public NonFood(String objekName, Point startPoint) {
        this(objekName, startPoint, startPoint);
    }

    // Method

    public int getObjLength() {
        return objLength;
    }

    public void setObjLength(int objLength) {
        this.objLength = objLength;
    }

    public int getObjWidth() {
        return objWidth;
    }

    public void setObjWidth(int objWidth) {
        this.objWidth = objWidth;
    }

    public int getObjPrice() {
        return objPrice;
    }

    public void setObjPrice(int price) {
        objPrice = price;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point starPoint) {
        this.startPoint = starPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
