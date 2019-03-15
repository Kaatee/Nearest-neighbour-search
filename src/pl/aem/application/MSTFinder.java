package pl.aem.application;

import java.util.ArrayList;

public class MSTFinder {
    private double[][] distanceArray ;
    private ArrayList<Point> pointsList;
    private boolean[] marked;
    private double length;

    /**
     * Class constructor specifying list of points in minimum spanning tree
     * @param pointsList
     */
    public MSTFinder(ArrayList<Point> pointsList){
        this.pointsList = pointsList;
        ComputeManager cm = new ComputeManager(pointsList);
        this.distanceArray = cm.calculateDistanceArray();
        this.marked = new boolean[pointsList.size()];
        this.length = 0.0;
    }

    /**
     * iterate by all points and find the one which has minimum distance tree's points.
     * Then add this points to tree, mark this points as choosen and set new tree length
     */
    private void findNearestPointIdx(){
        int minIdxJ =0; //which point has min distance
        int minIdxI = 0; //for what point
        double minValue = Integer.MAX_VALUE;
        for (int i=0;i<pointsList.size(); i++){
            if(marked[i]) {
                for(int j = 0; j < pointsList.size(); j++) {
                    if(distanceArray[i][j]!=0 && j>i && distanceArray[i][j] < minValue){
                        minValue = distanceArray[i][j];
                        minIdxJ = j;
                        minIdxI = i;
                    }
                }
            }
        }
        length+=distanceArray[minIdxI][minIdxJ];
        distanceArray[minIdxI][minIdxJ] = 0;
        distanceArray[minIdxJ][minIdxI] = 0;
        marked[minIdxJ] = true;
    }

    /**
     * invoke findNearestPointIdx() until all points are in tree
     * @return point's minimum spanning tree length
     */
    public double findMSTlength() {
        marked[0]=true;

        int i=pointsList.size();
        while(i>1){
            i--;
            findNearestPointIdx();
        }
        return length;
    }

}
