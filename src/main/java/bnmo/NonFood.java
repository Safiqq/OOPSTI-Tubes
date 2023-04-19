package bnmo;

public class NonFood extends Objek {
    private int objLength;
    private int objWidth;
    private int objPrice;
    private Action action;
    private Point startPoint;
    private Point endPoint;

    //Konstruktor jangan lupa

    public NonFood(String objekName, int objLength, int objWidth, int objPrice, Action action, Point starPoint, Point endPoint){
        super(objekName);
        this.objLength = objLength;
        this.objWidth = objWidth;
        this.objPrice = objPrice;
        this.action = action;
        this.startPoint = starPoint;
        this.endPoint = endPoint;
    }

    public NonFood(String objekName){
        super(objekName);
    }

    public NonFood(String objekName, int objLength, int objWidth, int objPrice){
        super(objekName);
        this.objLength = objLength;
        this.objWidth = objWidth;
        this.objPrice = objPrice;
    }

    public NonFood(String objekName, Action action){
        super(objekName);
        this.action = action;
    }
    
    public NonFood(String objekName, Point starPoint, Point endPoint){
        super(objekName);
        this.startPoint = starPoint;
        this.endPoint = endPoint;
    }

    public int getObjLength(){
        return objLength;
    }

    public int getObjWidth(){
        return objWidth;
    }

    public int getObjPrice(){
        return objPrice;
    }

    public Action getAction(){
        return action;
    }

    public Point getStartPoint(){
        return startPoint;
    }

    public Point getEndPoint(){
        return endPoint;
    }

    public void setObjLength(int objLength){
        this.objLength = objLength;
    }

    public void setObjWidth(int objWidth){
        this.objWidth = objWidth;
    }

    public void setObjPrice(int price){
        objPrice = price;
    }

    public void setAction(Action action){
        this.action = action;
    }

    public void setStartPoint(Point starPoint){
        this.startPoint = starPoint;
    }

    public void setEndPoint(Point endPoint){
        this.endPoint = endPoint;
    }      
}
