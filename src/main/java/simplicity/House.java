package simplicity;

import java.util.ArrayList;
import java.util.Random;

public class House {
    private final String owner;
    private final Point houseLoc;
    private final Room defaultRoom;
    private final ArrayList<Room> listRoom = new ArrayList<>();

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

    public void addListRoom(Room room) {
        listRoom.add(room);
    }
}
