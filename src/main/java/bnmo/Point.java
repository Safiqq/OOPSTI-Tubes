package bnmo;

public class Point {
    private Integer x;
    private Integer y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
