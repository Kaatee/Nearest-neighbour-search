package pl.aem.application;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    private static final double DELTA = 1e-15;

    Group group1;
    Group group2;
    InstancesMaker im;

    @Before
    public void setup(){
        im = new InstancesMaker();
        group1 = new Group(im.getGroup1().getGroupColor());
        group2 = new Group(im.getGroup2().getGroupColor());

        group1.setPointsInGroup(im.getPointsList1());
        group2.setPointsInGroup(im.getPointsList2());
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