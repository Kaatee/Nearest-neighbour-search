package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class Group {

    private ArrayList<Point> pointsInGroup;
    private Color groupColor;

    public Group(Color color){
        this.pointsInGroup= new ArrayList<>();
        this.groupColor=color;
    }

    public ArrayList<Point> getPointsInGroup() {
        return pointsInGroup;
    }

    public void setPointsInGroup(ArrayList<Point> pointsInGroup) {
        this.pointsInGroup = pointsInGroup;
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
