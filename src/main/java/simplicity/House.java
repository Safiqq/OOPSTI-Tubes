package simplicity;
import java.util.ArrayList;


public class House {
    // Attribute
    private String owner = sim.getFullName();
    private ArrayList<Room> listRoom;
    private Point houseLoc;
    private int emptyRoom;
    private Room defaultRoom;

    //constructor
    public House(Point houseLoc){
        this.houseLoc = houseLoc;
    }

    //methods getter
    public String getOwner(){
        return owner;
    }

    public Point getHouseLoc(){
        return houseLoc;
    }

    public Room getDefaultRoom(){
        return defaultRoom;
    }

    // public int getEmptyRoom(){
    //     return emptyRoom;
    // }

    public ArrayList<Room> getListRoom(){
        return listRoom;
    }

    //methods setter
    public void setHouseLoc(Point houseLoc){
        this.houseLoc = houseLoc;
    }

    public void setListRoom(ArrayList<Room> listRoom){
        this.listRoom = listRoom;
    }

    public void setEmptyRoom(int emptyRoom){
        this.emptyRoom = emptyRoom;
    }

    //other methods
    public void addListRoom(Room room){
        listRoom.add(room);
    }
}
