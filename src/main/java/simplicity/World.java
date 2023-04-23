package simplicity;

public class World {
    // attribute
    private static World world = new World();
    private final int worldLength = 64;
    private final int worldWidth = 64;
    private House[][] matrixHouse;

    // constructor
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

    // methods getter
    public int getWorldLength() {
        return worldLength;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public House[][] getMatrixHouse() {
        return matrixHouse;
    }

    // method setter
    public void setMatrixHouse(House[][] matrixHouse) {
        this.matrixHouse = matrixHouse;
    }

    // other methods
    public boolean isWorldAvail(Point houseLoc){
        if(matrixHouse[houseLoc.getX()][houseLoc.getY()] == null){
            return true;
        } else {
            return false;
        }
    }

    public boolean isHouseBuildAble(){
        // true jika ada spot kosong untuk membangun rumah
        // false jika tidak ada
        for(int i = 0; i < 64; i++){
            for (int j = 0; j < 64; j++){
                if (matrixHouse[i][j] == null){
                    return true;
                }
            }
        }
        
        return false;
    }

    public void addHouse(House house) {
        // add house ke world
        if (isWorldAvail(house.getHouseLoc())){
            matrixHouse[house.getHouseLoc().getX()][house.getHouseLoc().getY()] = house;
        } else {
            System.out.println("Sudah ada rumah Sim lain");
        }

        // try{
        // if (matrixHouse[houseLoc.getX()][houseLoc.getY()] != null) {
        //     matrixHouse[houseLoc.getX()][houseLoc.getY()] = house;
        // } else {
        //     System.out.println("Sudah ada rumah Sim lain");
        //     // throw new Exception("Sudah ada rumah Sim lain");
        // }
        // }
        // catch(Exception e){
        // System.out.println(e.getMessage());
        // }
    }

    public void printMatrixHouse() {
        // menampilkan world
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldLength; j++) {
                System.out.print(matrixHouse[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}