package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ComputeManagerTest {

    private static final double DELTA = 1e-15;

    Point p0 = new Point(0,0);
    Point p1 = new Point(4,0);
    Point p2 = new Point(4,4);
    Point p3 = new Point(0,4);

    ArrayList<Point> pointsList = new ArrayList<>();
    ComputeManager cm;

    @Before
    public void setup(){
        pointsList.add(p0);
        pointsList.add(p1);
        pointsList.add(p2);
        pointsList.add(p3);

        cm = new ComputeManager(pointsList);
    }

    @Test
    public void calculateDistanceArray() {
        double sqrtX4 = 4.0*Math.sqrt(2);
        double[][] array = new double[][] {{0.0, 4.0, sqrtX4, 4.0},{4.0, 0.0, 4.0 , sqrtX4},{sqrtX4, 4.0, 0.0, 4.0},{4.0, sqrtX4, 4.0, 0.0}};

        assertArrayEquals(array, cm.calculateDistanceArray());
    }

    @Test
    public void maxXRange() {
        assertEquals(cm.maxXRange(), 4.0, DELTA);
    }

    @Test
    public void maxYRange() {
       assertEquals(cm.maxYRange(), 4.0, DELTA);
    }
}