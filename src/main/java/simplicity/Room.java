package simplicity;

import java.util.ArrayList;

public class Room {
    // attribute
    private String roomName;
    private final int roomLength = 6;
    private final int roomWidth = 6;
    private Room upperSide;
    private Room bottomSide;
    private Room leftSide;
    private Room rightSide;
    private ArrayList<NonFood> listObjek;
    // new atribute
    private boolean[][] emptyRoom = new boolean[roomLength][roomWidth];

    public Room(String roomName) {
        this.roomName = roomName;
        this.upperSide = null;
        this.bottomSide = null;
        this.leftSide = null;
        this.rightSide = null;
        listObjek = new ArrayList<NonFood>();

        for (int i = 0; i < roomLength; i++) {
            for (int j = 0; j < roomWidth; j++) {
                emptyRoom[i][j] = true;
            }
        }
    }

    public Room(String roomName, Room upperSide, Room bottomSide, Room leftSide, Room rightSide) {
        this.roomName = roomName;
        this.upperSide = upperSide;
        this.bottomSide = bottomSide;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        listObjek = new ArrayList<NonFood>();

        for (int i = 0; i < roomLength; i++) {
            for (int j = 0; j < roomWidth; j++) {
                emptyRoom[i][j] = true;
            }
        }
    }

    public Room(String roomName, Room upperSide, Room bottomSide, Room leftSide, Room rightSide,
            ArrayList<NonFood> listObjek) {
        this.roomName = roomName;
        this.upperSide = upperSide;
        this.bottomSide = bottomSide;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.listObjek = listObjek;

        for (NonFood objek : listObjek) {
            int startX = objek.getStartPoint().getX();
            int startY = objek.getStartPoint().getY();
            int endX = objek.getEndPoint().getX();
            int endY = objek.getEndPoint().getY();
            // buat room jadi not empty sesuai dengan ukuran NonFood
            for (int i = 0; i < roomLength; i++) {
                for (int j = 0; j < roomWidth; j++) {
                    if ((i >= startX && i <= endX) && (j >= startY && j <= endY)) {
                        emptyRoom[i][j] = false;
                    } else {
                        emptyRoom[i][j] = true;
                    }

                }
            }

        }
    }

    // methods getter
    public String getRoomName() {
        return this.roomName;
    }

    public int getRoomLength() {
        return this.roomLength;
    }

    public int getRoomWidth() {
        return this.roomWidth;
    }

    public Room getUpperSide() {
        return this.upperSide;
    }

    public Room getBottomSide() {
        return this.bottomSide;
    }

    public Room getLeftSide() {
        return this.leftSide;
    }

    public Room getRightSide() {
        return this.rightSide;
    }

    public ArrayList<NonFood> getListObjek() {
        return this.listObjek;
    }

    // methods setter
    public void setUpperSide(Room room) {
        this.upperSide = room;
    }

    public void setBottomSide(Room room) {
        this.bottomSide = room;
    }

    public void setLeftSide(Room room) {
        this.leftSide = room;
    }

    public void setRightSide(Room room) {
        this.rightSide = room;
    }

    public void setListObjek(ArrayList<NonFood> listObjek) {
        this.listObjek = listObjek;
    }

    // other methods
    // new methods to check apakah space di ruangan kosong atau tidak
    public boolean isSpaceEmpty(Point startPoint, Point endPoint) {
        int startX = startPoint.getX();
        int startY = startPoint.getY();
        int endX = endPoint.getX();
        int endY = endPoint.getY();

        boolean status = true;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (emptyRoom[i][j] != true) {
                    status = false;
                    break;
                }
            }
        }
        return status;
    }

    public void addListObject(NonFood objek) throws Exception {
        // try{
        if (isSpaceEmpty(objek.getStartPoint(), objek.getEndPoint())) {
            listObjek.add(objek);
        } else {
            throw new Exception("Area ruangan yang dipilih tidak kosong. Silahkan Pilih area lain.");
        }
        // }
        // catch(Exception e){
        // System.out.println(e.getMessage());
        // }
    }
}
