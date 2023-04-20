package simplicity;

public class World {
    //attribute
    private final int worldLength = 64;
    private final int worldWidth = 64;
    private House[][] matrixHouse;

    //constructor
    public World(){
        matrixHouse = new House[worldLength][worldWidth];
        for (int i = 0 ; i < worldWidth ; i++){    
            for(int j = 0 ; j < worldLength ; j++){
                matrixHouse[i][j] = null;
            }
        }
    }

    //methods getter
    public int getWorldLength(){
        return worldLength;
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public House[][] getMatrixHouse(){
        return matrixHouse;
    }

    //method setter
    public void setMatrixHouse(House[][] matrixHouse){
        this.matrixHouse = matrixHouse;
    }

    //other methods

    public void addHouse(House house, Point point) throws Exception{
    //add house ke world
        try{
            if (matrixHouse[point.getX()][point.getY()] != null){
                matrixHouse[point.getX()][point.getY()] = house;
            }
            else{
                throw new Exception("Sudah ada rumah Sim lain");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void printMatrixHouse(){
    //menampilkan world
        for (int i = 0 ; i < worldWidth ; i++){    
            for(int j = 0 ; j < worldLength ; j++){
                System.out.print(matrixHouse[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}