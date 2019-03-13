package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ComputeManagerTest {

    Point p1 = new Point(0,0);
    Point p2 = new Point(4,0);
    Point p3 = new Point(4,4);
    Point p4 = new Point(0,4);

    @Test
    public void calculateDistanceArray() {
        
    }

    @Test
    public void maxXRange() {
    }

    @Test
    public void maxYRange() {
    }
}