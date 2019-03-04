import java.awt.*;

public class Point {

    private double xCoordinate;
    private double yCoordinate;
    private int groupId;
    private Color color;


    public Point (double x , double y){
        this.xCoordinate=x;
        this.yCoordinate=y;
        this.color=Color.BLACK;

    }

    public String toString(){
        return "Wspolrzedna x: " + xCoordinate + " , wspolrzedna y: " + yCoordinate;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
