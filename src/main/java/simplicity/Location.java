package simplicity;

public class Location {
    private House house;
    private Room room;
    private Point point;

    public Location(House house, Room room, Point point){
        this.house = house;
        this.room = room;
        this.point = point;
    }

    public House getHouse(){
        return house;
    }

    public Room getRoom(){
        return room;
    }

    public Point getPoint(){
        return point;
    }

    public void setHouse(House house){
        this.house = house;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public void setPoint(Point point){
        this.point = point;
    }
}
