package pl.aem.application;

import java.awt.*;
import java.util.ArrayList;

public class RegretAlgorithm extends Algorithm {

    protected double[][] regretArray;

    /**
     * Class constructor specifying list of points to be grouped by regret algorithm
     * @param list to be copied to pointsList
     */
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

    /**
     * @param array
     * @return index of max value in array
     */
    private int getIndexOfMax(double [] array){
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt;
    }

    /**
     * @param array
     * @return index of min value in array
     */
    private int getIndexOfMin(double [] array){
        int minAt = 0;
        for (int i = 0; i < array.length; i++) {
            minAt = array[i] < array[minAt] ? i : minAt;
        }
        return minAt;
    }

    /**
     * Main function of algorithm. Split all points into groups using algorithm based on regret
     * @param numberOfGroups
     * @param iteration
     */
    @Override
    public void splitIntoGroups(int numberOfGroups,int iteration) {
        regretArray = new double [pointsList.size()][numberOfGroups];
        listOfGroup=groupInit.makeGroupsRandomly(10);
        paintGroups();


        for (int i=0 ; i<pointsList.size()-numberOfGroups; i++){
            double [] rowSumOfPoints = new double[pointsList.size()];

            for (Point p : pointsList) {
                double currentSum=0.0;
               for (Group g : listOfGroup){
                   if(p.getColor().equals(Color.WHITE)){

                       double currentLength = g.getMstLen();
                       ArrayList<Point> pointsL = new ArrayList<>(g.getPointsInGroup());
                       pointsL.add(p);
                       newMST = new MSTFinder(pointsL);
                       double newLength = newMST.findMSTlength();
                       double dif = newLength - currentLength;

                       regretArray[pointsList.indexOf(p)][listOfGroup.indexOf(g)] = dif;
                       currentSum+=dif;
                   }
               }
               rowSumOfPoints[pointsList.indexOf(p)]=currentSum;
            }


            int pointIdx = getIndexOfMax(rowSumOfPoints);
            int groupIdx = getIndexOfMin(regretArray[pointIdx]);

            listOfGroup.get(groupIdx).addPoint(pointsList.get(pointIdx));
            listOfGroup.get(groupIdx).calculateCurrentCenter();
            listOfGroup.get(groupIdx).setMstLen(new MSTFinder(listOfGroup.get(groupIdx).getPointsInGroup()).findMSTlength());
            paintGroups();

            for (int j=0; j<pointsList.size() ; j++){
                rowSumOfPoints[j]=0.0;
            }

            for (int z=0;z<pointsList.size();z++) {
                for (int y = 0; y < numberOfGroups; y++) {
                    regretArray[z][y] = 0.0;
                }
            }

        }


        totalMSTValue=calculateMST();
        System.out.println("Regret: " + totalMSTValue);
        paintGroups();
        for (Point p : pointsList){
            if(p.getColor().equals(Color.WHITE)) System.out.println("error");
        }
        picture.draw("Results/ResultRegret"+iteration+".png");
    }
}
