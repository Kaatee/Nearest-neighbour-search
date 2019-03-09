package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class Group {

    private ArrayList<Point> pointsInGroup;
    private Color groupColor;
    private Double mstLen;
    private Double centerX;
    private Double centerY;

    public Group(Color color){
        this.pointsInGroup= new ArrayList<>();
        this.groupColor=color;
        this.mstLen = 0.0;
        this.centerX=0.0;
        this.centerY=0.0;
    }

    public ArrayList<Point> getPointsInGroup() {
        return pointsInGroup;
    }

    public void setPointsInGroup(ArrayList<Point> pointsInGroup) {
        this.pointsInGroup = pointsInGroup;
    }

    public void setMstLen(double len) {
        this.mstLen = len;
    }

    public double getMstLen() {
        return mstLen;
    }

    public Color getGroupColor() {
        return groupColor;
    }

    public void addPoint(Point p){
        this.pointsInGroup.add(p);
    }

    public void setColorPoints(){
        for(Point p : pointsInGroup){
            p.setColor(groupColor);
        }
    }

    public Double getCenterX() {
        return centerX;
    }

    public void setCenterX(Double center) {
        this.centerX = center;
    }


    public Double getCenterY() {
        return centerY;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    public void calculateCurrentCenter(){
        double sumX=0.0;
        double sumY=0.0;
        for(Point p :pointsInGroup){
            sumX+=p.getxCoordinate();
            sumY+=p.getyCoordinate();
        }

        this.centerX=sumX/pointsInGroup.size();
        this.centerY=sumY/pointsInGroup.size();
    }

}
