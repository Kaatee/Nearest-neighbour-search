package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public abstract class Algorithm {

    protected double[][] distanceArray;
    protected ArrayList<Point> pointsList;
    protected ArrayList<Group> listOfGroup;
    protected GroupInitializer groupInit;
    protected ComputeManager computeManager;
    protected DrawPicture picture;
    protected MSTFinder newMST;
    protected double totalMSTValue=0.0;

    protected abstract void splitIntoGroups(int numberOfGroups,int iteration);

    protected void paintGroups(){
        for (Group g : listOfGroup) {
            g.setColorPoints();
        }
    }

    protected double calculateMST(){
        double mstValue=0.0;
        for (Group x : listOfGroup) {
            mstValue += x.getMstLen();
        }
        return mstValue;
    }

    public double getTotalMSTvalue() {
        return totalMSTValue;
    }


}
