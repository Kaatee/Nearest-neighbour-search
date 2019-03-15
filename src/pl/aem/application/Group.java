package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class Group {

    private ArrayList<Point> pointsInGroup;
    private Color groupColor;
    private Double mstLen; //current length of minimum spanning tree
    private Double centerX;
    private Double centerY;

    /**
     * Group constructor. Set mstLen and center coordinates on 0.0
     * @param color
     */
    public Group(Color color){
        this.pointsInGroup= new ArrayList<>();
        this.groupColor=color;
        this.mstLen = 0.0;
        this.centerX=0.0;
        this.centerY=0.0;
    }

    /**
     * @return array list of points in group
     */
    public ArrayList<Point> getPointsInGroup() {
        return pointsInGroup;
    }

    /**
     * @param pointsInGroup - array list of points in group to set
     */
    public void setPointsInGroup(ArrayList<Point> pointsInGroup) {
        this.pointsInGroup = pointsInGroup;
    }

    /**
     * @param len of group's mst to set
     */
    public void setMstLen(double len) {
        this.mstLen = len;
    }

    /**
     * @return group's mst
     */
    public double getMstLen() {
        return mstLen;
    }

    /**
     * @return group's color
     */
    public Color getGroupColor() {
        return groupColor;
    }

    /**
     * @param p - point to be added to group
     */
    public void addPoint(Point p){
        this.pointsInGroup.add(p);
    }

    /**
     * set group's color to all points in group
     */
    public void setColorPoints(){
        for(Point p : pointsInGroup){
            p.setColor(groupColor);
        }
    }

    /**
     * @return group's center x coordinate
     */
    public Double getCenterX() {
        return centerX;
    }

    /**
     * @param center x coordinate to be set as group's center
     */
    public void setCenterX(Double center) {
        this.centerX = center;
    }


    /**
     * @return group's center y coordinate
     */
    public Double getCenterY() {
        return centerY;
    }

    /**
     * @param centerY to be set as group's center
     */
    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    /**
     * calculate and set current group's center
     * center is calculated as average of all x and y coordinates
     */
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
