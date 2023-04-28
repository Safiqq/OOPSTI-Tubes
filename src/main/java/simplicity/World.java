package simplicity;

public class World {
    private static final World world = new World();
    private final int worldLength = 65;
    private final int worldWidth = 65;
    private House[][] matrixHouse;

    private World() {
        matrixHouse = new House[worldLength][worldWidth];
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldLength; j++) {
                matrixHouse[i][j] = null;
            }
        }
    }

    public static World getWorld() {
        return world;
    }

    public int getWorldLength() {
        return worldLength;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public House[][] getMatrixHouse() {
        return matrixHouse;
    }

    public void setMatrixHouse(House[][] matrixHouse) {
        this.matrixHouse = matrixHouse;
    }

    public boolean isWorldAvail(Point houseLoc) {
        return matrixHouse[houseLoc.getX()][houseLoc.getY()] == null;
    }

    public boolean isHouseBuildAble() {
        // true jika ada spot kosong untuk membangun rumah
        // false jika tidak ada
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (matrixHouse[i][j] == null) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addHouse(String owner, Point houseLoc) {
        House house = new House(owner, houseLoc);
        addHouse(house);
    }

    public void addHouse(House house) {
        // add house ke world
        if (isWorldAvail(house.getHouseLoc())) {
            matrixHouse[house.getHouseLoc().getX()][house.getHouseLoc().getY()] = house;
        } else {
            System.out.println("Sudah ada rumah Sim lain");
        }

        // try{
        // if (matrixHouse[houseLoc.getX()][houseLoc.getY()] != null) {
        // matrixHouse[houseLoc.getX()][houseLoc.getY()] = house;
        // } else {
        // System.out.println("Sudah ada rumah Sim lain");
        // // throw new Exception("Sudah ada rumah Sim lain");
        // }
        // }
        // catch(Exception e){
        // System.out.println(e.getMessage());
        // }
    }

    public Point searchHouse(String owner) {
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldLength; j++) {
                if (matrixHouse[i][j].getOwner().equals(owner)) {
                    return new Point(i, j);
                }
            }
        }

        return null;
    }

    public void printMatrixHouse() {
        // menampilkan world
        System.out.println();
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldLength; j++) {
                if (matrixHouse[i][j] == null) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}