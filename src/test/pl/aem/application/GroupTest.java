package pl.aem.application;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    private static final double DELTA = 1e-15;

    private Group group1;
    private Group group2;

    Point p0 = new Point(0,0);
    Point p1 = new Point(3,0);
    Point p2 = new Point(3,3);
    Point p3 = new Point(0,3);

    @Before
    public void setup(){
        group1 = new Group(Color.BLACK);
        group1.addPoint(p0);
        group1.addPoint(p1);
        group1.addPoint(p2);
        group1.addPoint(p3);

        group2 = new Group(Color.RED);
        group2.addPoint(p0);
        group2.addPoint(p1);
        group2.addPoint(p2);
    }

    @Test
    public void calculateCurrentCenter() {
        group1.calculateCurrentCenter();
        group2.calculateCurrentCenter();

        //check X coordinate
        assertEquals(1.5, group1.getCenterX(), DELTA);
        assertEquals(2.0, group2.getCenterX(), DELTA);

        //check Y coordinate
        assertEquals(1.5, group1.getCenterY(), DELTA);
        assertEquals(1.0, group2.getCenterY(), DELTA);
    }
}