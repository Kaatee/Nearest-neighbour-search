package pl.aem.application;

import java.util.ArrayList;

public class MSTFinder {
    private double[][] distanceArray ;
    private ArrayList<Point> pointsList;
    private boolean[] marked;
    private double length;

    public MSTFinder(ArrayList<Point> pointsList){
        this.pointsList = pointsList;
        ComputeManager cm = new ComputeManager(pointsList);
        this.distanceArray = cm.calculateDistanceArray();
        this.marked = new boolean[pointsList.size()];
        this.length = 0.0;
    }

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

    public double findMSTlength() {
        marked[0]=true;

        int i=pointsList.size();
        while(i>0){
            i--;
            findNearestPointIdx();
        }

        return length;
    }



    public static boolean isAllTrue(boolean[] array)
    {
        for(boolean b : array) if(!b) return false;
        return true;
    }


}
