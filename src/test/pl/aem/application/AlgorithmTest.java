package pl.aem.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AlgorithmTest {
    private static final double DELTA = 1e-15;

    InstancesMaker im;
    Algorithm algorithm;

    @Before
    public void setup(){
        im = new InstancesMaker();
        algorithm = new Algorithm() {
            @Override
            protected void splitIntoGroups(int numberOfGroups, int iteration) {
                algorithm.listOfGroup = new ArrayList<>();
                algorithm.listOfGroup.add(im.getGroup1());
                algorithm.listOfGroup.add(im.getGroup2());
                algorithm.listOfGroup.add(im.getGroup3());
            }
        };
        algorithm.splitIntoGroups(3, 1000);

        for(int i=0;i<3; i++) {
            MSTFinder mstfinder = new MSTFinder(algorithm.listOfGroup.get(i).getPointsInGroup());
            double g0MST = mstfinder.findMSTlength();
            algorithm.listOfGroup.get(i).setMstLen(g0MST);
        }

    }

    @Test
    public void calculateMST() {
        assertEquals(30.0, algorithm.calculateMST(), DELTA);
    }
}