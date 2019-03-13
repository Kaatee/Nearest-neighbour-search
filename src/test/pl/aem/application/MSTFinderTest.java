package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MSTFinderTest {
    private static final double DELTA = 1e-15;

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

    ArrayList<Point> pointsList1 = new ArrayList<>();
    ArrayList<Point> pointsList2 = new ArrayList<>();

    MSTFinder mstFinder1;
    MSTFinder mstFinder2;

    @Before
    public void setup(){
        pointsList1.add(p0);
        pointsList1.add(p1);
        pointsList1.add(p2);
        pointsList1.add(p3);

        mstFinder1 = new MSTFinder(pointsList1);

        pointsList2.addAll(pointsList1);
        pointsList2.add(p4);
        pointsList2.add(p5);
        pointsList2.add(p6);
        pointsList2.add(p7);
        pointsList2.add(p8);
        pointsList2.add(p9);
        pointsList2.add(p10);
        pointsList2.add(p11);
        pointsList2.add(p12);
        pointsList2.add(p13);
        pointsList2.add(p14);
        pointsList2.add(p15);

        mstFinder2 = new MSTFinder(pointsList2);

    }

    @Test
    public void findMSTlength() {
        assertEquals(9.0, mstFinder1.findMSTlength(),DELTA);
        assertEquals(15.0, mstFinder2.findMSTlength(),DELTA);
    }

}