package simplicity;

import java.util.ArrayList;

public class Room {
    //attribute
    private String roomName;
    private final int roomLength = 6;
    private final int roomWidth =  6;
    private Room upperSide;
    private Room bottomSide;
    private Room leftSide;
    private Room rightSide;
    private ArrayList<Objek> listObjek;

    //constructor
    public Room(String roomName){
        this.roomName = roomName;
        this.upperSide = null;
        this.bottomSide = null;
        this.leftSide = null;
        this.rightSide = null;
        listObjek = new ArrayList<Objek>();
    }

    public Room(String roomName, Room upperSide, Room bottomSide, Room leftSide, Room rightSide){
        this.roomName = roomName;
        this.upperSide = upperSide;
        this.bottomSide = bottomSide;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        listObjek = new ArrayList<Objek>();
    }

    public Room(String roomName, Room upperSide, Room bottomSide, Room leftSide, Room rightSide, ArrayList<Objek> listObjek){
        this.roomName = roomName;
        this.upperSide = upperSide;
        this.bottomSide = bottomSide;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.listObjek = listObjek;
    }

    //methods getter
    public String getRoomName(){
        return this.roomName;
    }

    public int getRoomLength(){
        return this.roomLength;
    }

    public int getRoomWidth(){
        return this.roomWidth;
    }

    public Room getUpperSide(){
        return this.upperSide;
    }

    public Room getBottomSide(){
        return this.bottomSide;
    }

    public Room getLeftSide(){
        return this.leftSide;
    }

    public Room getRightSide(){
        return this.rightSide;
    }

    public ArrayList<Objek> getListObjek(){
        return this.listObjek;
    }

    //methods setter
    public void setUpperSide(Room room){
        this.upperSide = room;
    }

    public void setBottomSide(Room room){
        this.bottomSide = room;
    }

    public void setLeftSide(Room room){
        this.leftSide = room;
    }

    public void setRightSide(Room room){
        this.rightSide = room;
    }

    public void setListObjek(ArrayList<Objek> listObjek){
        this.listObjek = listObjek;
    }

    //other methods
    public void addListObject(Objek objek){
        this.listObjek.add(objek);
    }
}
