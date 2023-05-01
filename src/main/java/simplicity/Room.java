package simplicity;

import java.util.ArrayList;
import java.util.Objects;

public class Room {
    // attribute
    private final static int roomLength = 6;
    private final static int roomWidth = 6;
    private final String roomName;
    // new atribute
    private final NonFood[][] matrixBarang = new NonFood[roomLength][roomWidth];
    private Room upperSide;
    private Room bottomSide;
    private Room leftSide;
    private Room rightSide;
    private ArrayList<NonFood> listObjek;

    // default room
    public Room() {
        this("Ruang Utama");
        NonFood kasurSingle = new NonFood("Kasur single", new Point(), new Point(0, 3));
        NonFood toilet = new NonFood("Toilet", new Point(5, 5));
        NonFood komporGas = new NonFood("Kompor gas", new Point(2, 5), new Point(3, 5));
        NonFood mejaDanKursi = new NonFood("Meja dan kursi", new Point(2, 1), new Point(4, 3));
        NonFood jam = new NonFood("Jam", new Point(0, 5));

        insertBarang(kasurSingle);
        insertBarang(toilet);
        insertBarang(komporGas);
        insertBarang(mejaDanKursi);
        insertBarang(jam);
    }

    public Room(String roomName) {
        this(roomName, null, null, null, null);
    }

    public Room(String roomName, Room upperSide, Room bottomSide, Room leftSide, Room rightSide) {
        this(roomName, upperSide, bottomSide, leftSide, rightSide, new ArrayList<>());
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
            insertBarang(objek);
        }
    }

    public static int getRoomLength() {
        return roomLength;
    }

    public static int getRoomWidth() {
        return roomWidth;
    }

    public NonFood[][] getMatrixBarang() {
        return matrixBarang;
    }

    public void insertBarang(NonFood barang) {
        if (isSpaceEmpty(barang.getStartPoint(), barang.getEndPoint())) {
            for (int i = barang.getStartPoint().getY(); i <= barang.getEndPoint().getY(); i++) {
                for (int j = barang.getStartPoint().getX(); j <= barang.getEndPoint().getX(); j++) {
                    matrixBarang[i][j] = barang;
                }
            }
            addListObjek(barang);
        }
    }

    public String getRoomName() {
        return this.roomName;
    }

    public Room getUpperSide() {
        return this.upperSide;
    }

    public void setUpperSide(Room room) {
        this.upperSide = room;
    }

    public Room getBottomSide() {
        return this.bottomSide;
    }

    public void setBottomSide(Room room) {
        this.bottomSide = room;
    }

    public Room getLeftSide() {
        return this.leftSide;
    }

    public void setLeftSide(Room room) {
        this.leftSide = room;
    }

    public Room getRightSide() {
        return this.rightSide;
    }

    public void setRightSide(Room room) {
        this.rightSide = room;
    }

    public ArrayList<NonFood> getListObjek() {
        return this.listObjek;
    }

    public void setListObjek(ArrayList<NonFood> listObjek) {
        this.listObjek = listObjek;
    }

    // new methods to check apakah space di ruangan kosong atau tidak
    public boolean isSpaceEmpty(Point startPoint, Point endPoint) {
        for (int i = startPoint.getY(); i <= endPoint.getY(); i++) {
            for (int j = startPoint.getX(); j <= endPoint.getX(); j++) {
                if (Objects.nonNull(matrixBarang[i][j])) return false;
            }
        }
        return true;
    }

    public void addListObjek(NonFood objek) {
        listObjek.add(objek);
    }
}
