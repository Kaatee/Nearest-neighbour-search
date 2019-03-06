package pl.aem.application;

import java.util.ArrayList;

public class PointOrientedAlgorithm extends Algorithm {


    public PointOrientedAlgorithm( ArrayList<Point> list){
        this.pointsList = list;
        this.computeManager=new ComputeManager(pointsList);
        this.distanceArray = computeManager.calculateDistanceArray();
        this.listOfGroup = new ArrayList<>();
        this.groupInit=new GroupInitializer(distanceArray,pointsList);
        this.picture=new DrawPicture(pointsList,computeManager.maxXRange(),computeManager.maxYRange());
    }


    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {


        listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();
        //picture.draw("AfterGroupInitPointOriented.png");

        for(Point p: pointsList){

            int minGroupIdx =0;
            double minDiffer = Double.MAX_VALUE;

            for(int i=0;i<listOfGroup.size();i++){
                double currentLength = listOfGroup.get(i).getMstLen();

                ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(i).getPointsInGroup());
                pointsL.add(p);
                newMST = new MSTFinder(pointsL);
                double newLength = newMST.findMSTlength();

                double dif = newLength - currentLength;
                if(dif < minDiffer){
                    minDiffer = dif;
                    minGroupIdx = i;
                }
            }

            listOfGroup.get(minGroupIdx).addPoint(p);
            listOfGroup.get(minGroupIdx).setMstLen(listOfGroup.get(minGroupIdx).getMstLen()+minDiffer);
        }

        totalMSTValue=calculateMST();
        System.out.println("MST: " + totalMSTValue);
        paintGroups();
        picture.draw("Results/ResultPointOriented"+iteration+".png");
    }


}
