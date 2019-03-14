package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class InstancesMaker {
    private Group group1; //4 points
    private Group group2; //3 points
    private Group group3; //16 points

    private ArrayList<Point> pointsList1 = new ArrayList<>(); //4 points
    private ArrayList<Point> pointsList2 = new ArrayList<>(); //3 points
    private ArrayList<Point> pointsList3 = new ArrayList<>(); //16 points

    Point p0 = new Point(0,0);
    Point p1 = new Point(3,0);
    Point p2 = new Point(3,3);
    Point p3 = new Point(0,3);

    Point p4 = new Point(0,1);
    Point p5 = new Point(0,2);
    Point p6 = new Point(3,2);
    Point p7 = new Point(1,0);
    Point p8 = new Point(1,1);
    Point p9 = new Point(1,2);
    Point p10 = new Point(1,3);
    Point p11 = new Point(3,1);
    Point p12 = new Point(2,0);
    Point p13 = new Point(2,1);
    Point p14 = new Point(2,2);
    Point p15 = new Point(2,3);


    public InstancesMaker(){
        pointsList1.add(p0);
        pointsList1.add(p1);
        pointsList1.add(p2);
        pointsList1.add(p3);

        pointsList2.add(p0);
        pointsList2.add(p1);
        pointsList2.add(p2);

        pointsList3.addAll(pointsList1);
        pointsList3.add(p4);
        pointsList3.add(p5);
        pointsList3.add(p6);
        pointsList3.add(p7);
        pointsList3.add(p8);
        pointsList3.add(p9);
        pointsList3.add(p10);
        pointsList3.add(p11);
        pointsList3.add(p12);
        pointsList3.add(p13);
        pointsList3.add(p14);
        pointsList3.add(p15);

        group1 = new Group(Color.BLACK);
        group1.setPointsInGroup(pointsList1);

        group2 = new Group(Color.RED);
        group2.setPointsInGroup(pointsList2);

        group3 = new Group(Color.GREEN);
        group3.setPointsInGroup(pointsList3);
    }


    public Group getGroup1() {
        return group1;
    }

    public Group getGroup2() {
        return group2;
    }

    public Group getGroup3() {
        return group3;
    }

    public ArrayList<Point> getPointsList1() {
        return pointsList1;
    }

    public ArrayList<Point> getPointsList2() {
        return pointsList2;
    }

    public ArrayList<Point> getPointsList3() {
        return pointsList3;
    }
}
