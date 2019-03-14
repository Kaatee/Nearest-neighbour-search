package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ComputeManagerTest {

    private static final double DELTA = 1e-15;

    InstancesMaker im;
    ComputeManager cm;

    @Before
    public void setup(){
        im = new InstancesMaker();
        cm = new ComputeManager(im.getPointsList1());
    }

    @Test
    public void calculateDistanceArray() {
        ///TODO
        double sqrtX3 = 3.0*Math.sqrt(2.0);
        double[][] array = new double[][] {{0.0, 3.0, sqrtX3, 3.0},{3.0, 0.0, 3.0 , sqrtX3},{sqrtX3, 3.0, 0.0, 3.0},{3.0, sqrtX3, 3.0, 0.0}};

        assertEquals(array.length, cm.calculateDistanceArray().length);

        for(int i=0; i<array.length; i++){
            assertArrayEquals(array[i], cm.calculateDistanceArray()[i], DELTA);
        }
    }

    @Test
    public void maxXRange() {
        assertEquals(cm.maxXRange(), 3.0, DELTA);
    }

    @Test
    public void maxYRange() {
       assertEquals(cm.maxYRange(), 3.0, DELTA);
    }
}