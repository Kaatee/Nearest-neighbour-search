package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class CenterOrientedAlgorithm extends Algorithm {


    /**
     * Class constructor specifying list of points to be grouped by center oriented algorithm
     * @param list
     */
    public CenterOrientedAlgorithm( ArrayList<Point> list){
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
     * Main function of algorithm. Split all points into groups using center oriented algorithm.
     * This algorithm iterate by all points and find such a group that has minimum current and new (after adding this point)
     * distence to group center (based on average coordinates) and add this point to that group
     * @param numberOfGroups
     * @param iteration
     */
    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {

        listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();
        //picture.draw("AfterGroupInitPointOriented.png");

        for (Group g : listOfGroup){
            g.calculateCurrentCenter();
        }

        for(Point p: pointsList){
            if(p.getColor().equals(Color.WHITE)) {
                // System.out.println(p.getColor());
                int minGroupIdx = 0;
                double minDiffer = Double.MAX_VALUE;

                for (int i = 0; i < listOfGroup.size(); i++) {
                    double distanceToGroup = Math.sqrt(Math.pow((p.getxCoordinate() - listOfGroup.get(i).getCenterX()), 2) + Math.pow((p.getyCoordinate() - listOfGroup.get(i).getCenterY()), 2));

                    if (distanceToGroup < minDiffer) {
                        minDiffer = distanceToGroup;
                        minGroupIdx = i;
                    }
                }
                listOfGroup.get(minGroupIdx).addPoint(p);
                listOfGroup.get(minGroupIdx).calculateCurrentCenter();
                listOfGroup.get(minGroupIdx).setMstLen(new MSTFinder(listOfGroup.get(minGroupIdx).getPointsInGroup()).findMSTlength());
            }
        }

        totalMSTValue=calculateMST();
        System.out.println("Center: " + totalMSTValue);
        paintGroups();
        picture.draw("Results/ResultCenterOriented"+iteration+".png");
    }

}
