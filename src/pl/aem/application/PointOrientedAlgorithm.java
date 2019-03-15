package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class PointOrientedAlgorithm extends Algorithm {


    /**
     * Class constructor specifying list of points to be grouped by point oriented algorithm
     * @param list to be copied to pointsList
     */
    public PointOrientedAlgorithm( ArrayList<Point> list){
        this.pointsList=new ArrayList<>();
        for(Point p : list){
            pointsList.add(new Point(p.getxCoordinate(),p.getyCoordinate()));
        }
        this.computeManager=new ComputeManager(pointsList);
        this.distanceArray = computeManager.calculateDistanceArray();
        this.listOfGroup = new ArrayList<>();
        this.groupInit=new GroupInitializer(distanceArray,pointsList);
        this.picture=new DrawPicture(pointsList,computeManager.maxXRange(),computeManager.maxYRange());
    }


    /**
     * Main function of algorithm. Split all points into groups using point oriented algorithm.
     * This algorithm iterate by all points and find such a group that has minimum current and new (after adding this point)
     * mst value and add this point to that group
     * @param numberOfGroups
     * @param iteration
     */
    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {

        listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();
        //picture.draw("AfterGroupInitPointOriented.png");

        for(Point p: pointsList){
            if(p.getColor().equals(Color.WHITE)) {
                int minGroupIdx = 0;
                double minDiffer = Double.MAX_VALUE;

                for (int i = 0; i < listOfGroup.size(); i++) {
                    double currentLength = listOfGroup.get(i).getMstLen();

                    ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(i).getPointsInGroup());
                    pointsL.add(p);
                    newMST = new MSTFinder(pointsL);
                    double newLength = newMST.findMSTlength();

                    double dif = newLength - currentLength;
                    if (dif < minDiffer) {
                        minDiffer = dif;
                        minGroupIdx = i;
                    }
                }

                listOfGroup.get(minGroupIdx).addPoint(p);
                listOfGroup.get(minGroupIdx).setMstLen(listOfGroup.get(minGroupIdx).getMstLen() + minDiffer);
            }
            }

        totalMSTValue=calculateMST();
        System.out.println("Point: " + totalMSTValue);
        paintGroups();
        picture.draw("Results/ResultPointOriented"+iteration+".png");
    }
}
