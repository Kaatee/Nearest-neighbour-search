package pl.aem.application;

import java.util.ArrayList;

public class App {

    public static void main(String[] args){

        String fileName= "Data/objects.data";
        FileManager fileManager = new FileManager();
        ArrayList<Point>  pointsList = fileManager.loadProblemData(fileName);

        ComputeManager computeManager= new ComputeManager(pointsList);
        double [][] distanceArray = computeManager.calculateDistanceArray();

        /*IAlgorithm regretAlgorithm  = new RegretHeuristicAlgorithm(distanceArray,pointsList);
        regretAlgorithm.splitIntoGroups(10);
*/
        IAlgorithm regretAlgorithm  = new MyAlgorithm(distanceArray,pointsList);
        regretAlgorithm.splitIntoGroups(10);

        DrawPicture picture = new DrawPicture(pointsList,computeManager.maxXRange(), computeManager.maxYRange());
        picture.draw("resultMy.png");

    }
}
