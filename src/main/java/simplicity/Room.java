package simplicity;

import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private final int roomLength = 6;
    private final int roomWidth = 6;
    // attribute
    private final String roomName;
    // new atribute
    private final boolean[][] emptyRoom = new boolean[roomLength][roomWidth];
    private final NonFood[][] matrixBarang = new NonFood[roomLength][roomWidth];
    private Room upperSide;
    private Room bottomSide;
    private Room leftSide;
    private Room rightSide;
    private ArrayList<NonFood> listObjek;

    // default room
    public Room() {
        this("RUANG UTAMA");
        NonFood kasurSingle = new NonFood("Kasur single", new Point(), new Point(0, 3));
        NonFood komporGas = new NonFood("Kompor gas", new Point(2, 5), new Point(3, 5));
        NonFood mejaDanKursi = new NonFood("Meja dan kursi", new Point(2, 1), new Point(4, 3));
        NonFood jam = new NonFood("Jam", new Point(0, 5));

        insertBarang(kasurSingle);
        insertBarang(komporGas);
        insertBarang(mejaDanKursi);
        insertBarang(jam);
    }

    public Room(String roomName) {
        (this.roomName = roomName).toUpperCase();
        upperSide = null;
        bottomSide = null;
        leftSide = null;
        rightSide = null;
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
                    emptyRoom[i][j] = (i < startX || i > endX) || (j < startY || j > endY);

                }
            }

        }
        // insert barang ke matrixBarang
        for (NonFood barang : listObjek){
            insertBarang(barang);
        }
    }

    public void insertBarang(NonFood barang) {
        if (isSpaceEmpty(barang.getStartPoint(), barang.getEndPoint())) {
            for (int i = barang.getStartPoint().getY(); i <= barang.getEndPoint().getY(); i++) {
                for (int j = barang.getStartPoint().getX(); j <= barang.getEndPoint().getX(); j++) {
                    matrixBarang[i][j] = barang;
                }
            }
            addListObjek(barang);
//        } else {
////            throw new Exception("Area ruangan yang dipilih tidak kosong. Silahkan pilih area lain.");
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

    // methods setter
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

    // other methods
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
        // try{
        listObjek.add(objek);
        // }
        // catch(Exception e){
        // System.out.println(e.getMessage());
        // }
    }
}
