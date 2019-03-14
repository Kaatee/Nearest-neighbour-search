package pl.aem.application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileManagerTest {
    private static final double DELTA = 1e-15;

    InstancesMaker im;
    FileManager fm;
    @Before
    public void setup() {
        im = new InstancesMaker();
        fm = new FileManager();
    }

    @Test
    public void loadProblemData() {
        System.out.println();
        assertEquals(im.getPointsList1().size(), fm.loadProblemData("Data/objectsTest.data").size());

        for(int i=0; i<im.getPointsList1().size(); i++){
            assertEquals(im.getPointsList1().get(i).getxCoordinate(), fm.loadProblemData("Data/objectsTest.data").get(i).getxCoordinate(), DELTA);
            assertEquals(im.getPointsList1().get(i).getyCoordinate(), fm.loadProblemData("Data/objectsTest.data").get(i).getyCoordinate(), DELTA);
        }
    }
}