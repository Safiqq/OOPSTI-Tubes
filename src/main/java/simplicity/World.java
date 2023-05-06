package simplicity;

import java.util.Arrays;

public class World {
    private static final World world = new World();
    private final int length = 65;
    private final int width = 65;
    private House[][] matrixHouse;

    private World() {
        matrixHouse = new House[length][width];
        for (int i = 0; i < width; i++) {
            Arrays.fill(matrixHouse[i], null);
        }
    }

    public static World getWorld() {
        return world;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public House[][] getMatrixHouse() {
        return matrixHouse;
    }

    public void setMatrixHouse(House[][] matrixHouse) {
        this.matrixHouse = matrixHouse;
    }

    public boolean isWorldAvail(Point houseLoc) {
        return matrixHouse[houseLoc.getY()][houseLoc.getX()] == null;
    }

    public boolean isHouseBuildAble() {
        // true jika ada spot kosong untuk membangun rumah
        // false jika tidak ada
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (matrixHouse[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addHouse(House house) {
        // add house ke world
        matrixHouse[house.getHouseLoc().getY()][house.getHouseLoc().getX()] = house;
    }

    public Point searchHouse(String owner) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (matrixHouse[i][j] != null) {
                    if (Main.equals(matrixHouse[i][j].getOwner(), owner)) {
                        return new Point(j, i);
                    }
                }
            }
        }
        return null;
    }

    public House findHouse(Point houseLoc) {
        return matrixHouse[houseLoc.getY()][houseLoc.getX()];
    }

    public void deleteHouse(Sim deleted){
        String simName = deleted.getFullName();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < length; j++){
                if (Main.equals(matrixHouse[i][j].getOwner(), simName)){
                    // hapus rumah Sim
                    matrixHouse[i][j] = null;
                }
            }
        }
    }

    public void printMatrixHouse() {
        // menampilkan world
        System.out.println();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (matrixHouse[i][j] == null) {
                    System.out.print(0);
                } else {
                    System.out.print(1);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}