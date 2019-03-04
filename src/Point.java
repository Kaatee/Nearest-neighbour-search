public class Point {

    private double xCoordinate;
    private double yCoordinate;
    private int groupId;


    public Point (double x , double y){
        this.xCoordinate=x;
        this.yCoordinate=y;
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
}
