package pl.aem.application;

import java.util.ArrayList;
import java.util.Random;

public class RegretHeuristicAlgorithm implements IAlgorithm {

    private double[][] distanceArray ;
    private ArrayList<Point> pointsList ;

    public RegretHeuristicAlgorithm(double [][] distanceArray, ArrayList<Point> list){
        this.distanceArray=distanceArray;
        this.pointsList=list;
    }

    @Override
    public void makeGroups(int numberOfGroups) {
        for(Point p : pointsList){
            p.setColor(colors[new Random().nextInt(colors.length)]);
        }
    }
}
