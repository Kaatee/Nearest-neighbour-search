package pl.aem.application;

import java.util.ArrayList;

public class GroupOrientedAlgorithm extends Algorithm {


    public GroupOrientedAlgorithm( ArrayList<Point> list) {
        this.pointsList = list;
        this.computeManager=new ComputeManager(pointsList);
        this.distanceArray = computeManager.calculateDistanceArray();
        this.listOfGroup = new ArrayList<>();
        this.groupInit=new GroupInitializer(distanceArray,pointsList);
        this.picture=new DrawPicture(pointsList,computeManager.maxXRange(),computeManager.maxYRange());
    }

    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {
        int addedPoint = 0;

        this.listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();
        //picture.draw("AfterGroupInitGroupOriented.png");

        ArrayList<Point> pointsToAdd = new ArrayList(pointsList);

        while (addedPoint < pointsList.size() - 1) {
            for (int g = 0; g < listOfGroup.size(); g++) {
                int minPointIdx = 0;
                double minDiffer = Double.MAX_VALUE;
                ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(g).getPointsInGroup());
                double currentLength = listOfGroup.get(g).getMstLen();

                for (int i = 0; i < pointsToAdd.size(); i++) {
                    pointsL.add(pointsToAdd.get(i));
                    newMST = new MSTFinder(pointsL);

                    double newLength = newMST.findMSTlength();
                    double diff = newLength - currentLength;

                    if (diff < minDiffer) {
                        minDiffer = diff;
                        minPointIdx = i;
                    }
                }

                listOfGroup.get(g).addPoint(pointsToAdd.get(minPointIdx));
                listOfGroup.get(g).setMstLen(listOfGroup.get(g).getMstLen() + minDiffer);
                addedPoint++;
                pointsToAdd.remove(pointsToAdd.get(minPointIdx));
                if (addedPoint > pointsList.size()) break;
            }
        }

        totalMSTValue=calculateMST();
        System.out.println("MST: " + totalMSTValue);
        paintGroups();
        picture.draw("Results/ResultGroupOriented"+iteration+".png");
    }
}

