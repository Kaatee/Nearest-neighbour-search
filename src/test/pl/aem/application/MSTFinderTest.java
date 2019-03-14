package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MSTFinderTest {
    private static final double DELTA = 1e-15;

    InstancesMaker im;

    MSTFinder mstFinder1;
    MSTFinder mstFinder2;

    @Before
    public void setup(){
        im = new InstancesMaker();

        mstFinder1 = new MSTFinder(im.getPointsList1());
        mstFinder2 = new MSTFinder(im.getPointsList3());

    }

    @Test
    public void findMSTlength() {
        assertEquals(9.0, mstFinder1.findMSTlength(),DELTA);
        assertEquals(15.0, mstFinder2.findMSTlength(),DELTA);
    }

}