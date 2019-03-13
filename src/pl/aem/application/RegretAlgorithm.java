package pl.aem.application;

import java.util.ArrayList;

public class RegretAlgorithm extends Algorithm {

    protected double[][] regretArray;

    public RegretAlgorithm( ArrayList<Point> list){
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
    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {
        regretArray = new double [pointsList.size()][numberOfGroups];
        listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();

        for (int i=0 ; i<pointsList.size()-10; i++){






        }


        totalMSTValue=calculateMST();
        System.out.println("Point: " + totalMSTValue);
        paintGroups();
        picture.draw("Results/ResultRegret"+iteration+".png");
    }
}
