package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class Group {

    private ArrayList<Point> pointsInGroup;
    private Color groupColor;
    private Double mstLen;

    public Group(Color color){
        this.pointsInGroup= new ArrayList<>();
        this.groupColor=color;
        this.mstLen = 0.0;
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
}
