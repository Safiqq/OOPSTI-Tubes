package simplicity;

import java.util.ArrayList;
import java.util.Random;

public class House {
    private final String owner;
    private final Point houseLoc;
    private final Room defaultRoom;
    private final ArrayList<Room> listRoom = new ArrayList<>();

    public House(String owner) {
        this(owner, new Point(new Random().nextInt(World.getWorld().getWorldLength()), new Random().nextInt(World.getWorld().getWorldWidth())));
    }

    public House(String owner, Point houseLoc) {
        this.owner = owner;
        this.houseLoc = houseLoc;
        defaultRoom = new Room("Ruang Utama");
        listRoom.add(defaultRoom);

        World.getWorld().addHouse(this);
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

    public void addListRoom(Room room) throws Exception {
        // check apakah area sekitar untuk menambah room kosong atau enggak
        if ((room.getUpperSide() == null) && (room.getBottomSide() == null) && (room.getLeftSide() == null) && (room.getRightSide() == null)) {
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
    }
}
