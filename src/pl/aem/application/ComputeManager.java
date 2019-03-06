package pl.aem.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComputeManager {
    private ArrayList<Point> pointsList;


    public ComputeManager(ArrayList<Point> list ){
        this.pointsList=list;

    }


    public  double[][] calculateDistanceArray() {
        double[][] distanceArray = new double[pointsList.size()][pointsList.size()];

        for (Point a : pointsList) {
            for (Point b : pointsList) {
                double distance = Math.sqrt(Math.pow((a.getxCoordinate() - b.getxCoordinate()), 2) + Math.pow((a.getyCoordinate() - b.getyCoordinate()), 2));
                distanceArray[pointsList.indexOf(a)][pointsList.indexOf(b)] = distance;
            }
        }
        return distanceArray;
    }

//
//    public  double minXRange(){
//        ComparatorX x  = new ComparatorX();
//        Point minXRangePoint=Collections.min(pointsList,x);
//        return  minXRangePoint.getxCoordinate();
//    }


    public  double maxXRange(){
        ComparatorX x  = new ComparatorX();
        Point minXRangePoint=Collections.max(pointsList,x);
        return  minXRangePoint.getxCoordinate();
    }

//    public  double minYRange(){
//        ComparatorY y  = new ComparatorY();
//        Point minXRangePoint=Collections.min(pointsList,y);
//        return  minXRangePoint.getyCoordinate();
//    }

    public  double maxYRange(){
        ComparatorY y  = new ComparatorY();
        Point minXRangePoint=Collections.max(pointsList,y);
        return  minXRangePoint.getyCoordinate();
    }

    private class ComparatorX implements Comparator<Point>{
        public int compare(Point o1, Point o2) {
            if (o1.getxCoordinate() == o2.getxCoordinate()) {
                return 0;
            } else if (o1.getxCoordinate() > o2.getxCoordinate()) {
                return 1;
            } else if (o1.getxCoordinate() < o2.getxCoordinate()) {
                return -1;
            }
            return 0;
        }
    }

    private class ComparatorY implements Comparator<Point>{
        public int compare(Point o1, Point o2) {
            if (o1.getyCoordinate() == o2.getyCoordinate()) {
                return 0;
            } else if (o1.getyCoordinate() > o2.getyCoordinate()) {
                return 1;
            } else if (o1.getyCoordinate() < o2.getyCoordinate()) {
                return -1;
            }
            return 0;
        }
    }

}
