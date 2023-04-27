package simplicity;

import java.util.ArrayList;
import java.util.Random;

public class House {
    // Attribute
    private final String owner;
    private ArrayList<Room> listRoom = new ArrayList<>();
    private final Point houseLoc;
    private final Room defaultRoom;

    // constructor
    public House(String owner) {
        Random random = new Random();
        Point point = new Point(random.nextInt(World.getWorld().getWorldLength()),
                random.nextInt(World.getWorld().getWorldWidth()));

        this.owner = owner;
        houseLoc = point;
        defaultRoom = new Room();
        listRoom.add(defaultRoom);
    }

    public House(String owner, Point houseLoc) {
        this.owner = owner;
        this.houseLoc = houseLoc;
        defaultRoom = new Room("Ruang Utama");
        listRoom.add(defaultRoom);
    }

    // methods getter
    public String getOwner() {
        return owner;
    }

    public Point getHouseLoc() {
        return houseLoc;
    }

    public Room getDefaultRoom() {
        return defaultRoom;
    }

    public ArrayList<Room> getListRoom() {
        return listRoom;
    }

    // methods setter
    // // kayaknya gaperlu deh, bbikin jd permanen aja
    // public void setHouseLoc(Point houseLoc){
    // this.houseLoc = houseLoc;
    // }

    // public void setListRoom(ArrayList<Room> listRoom){
    // this.listRoom = listRoom;
    // }

    // other methodss

    public void addListRoom(Room room) throws Exception {
        // check apakah area sekitar untuk menambah room kosong atau enggak
        // try{
        if ((room.getUpperSide() == null) && (room.getBottomSide() == null) && (room.getLeftSide() == null)
                && (room.getRightSide() == null)) {
            throw new Exception("Ruangan harus bersebelahan dengan ruangan lain");
        } else if (room.getLeftSide() != null) {
            if ((room.getLeftSide()).getRightSide() != null) {
                throw new Exception("Lokasi sudah diisi ruangan lain.");
            }
        } else if (room.getRightSide() != null) {
            if ((room.getRightSide()).getLeftSide() != null) {
                throw new Exception("Lokasi sudah diisi ruangan lain.");
            }
        } else if (room.getUpperSide() != null) {
            if ((room.getUpperSide()).getBottomSide() != null) {
                throw new Exception("Lokasi sudah diisi ruangan lain.");
            }
        } else if (room.getBottomSide() != null) {
            if ((room.getBottomSide()).getUpperSide() != null) {
                throw new Exception("Lokasi sudah diisi ruangan lain.");
            }
        } else {
            listRoom.add(room);
        }
        // }
        // catch (Exception e){
        // System.out.println(e.getMessage());
        // }

    }
}
