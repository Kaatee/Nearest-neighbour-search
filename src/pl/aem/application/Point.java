package pl.aem.application;

import java.awt.*;

public class Point {

    private double xCoordinate;
    private double yCoordinate;
    private Color color;

    /**
     * Class constructor specifying point's x and y coordinate
     * @param x
     * @param y
     */
    public Point (double x , double y){
        this.xCoordinate=x;
        this.yCoordinate=y;
        this.color=Color.WHITE;
    }

    /**
     * @return string value of point
     */
    public String toString(){
        return "Wspolrzedna x: " + xCoordinate + " , wspolrzedna y: " + yCoordinate;
    }

    /**
     * @return point's x coordinate
     */
    public double getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @return point's y coordinate
     */
    public double getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param color to be set as point's color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return point's color
     */
    public Color getColor() {
        return color;
    }
}
