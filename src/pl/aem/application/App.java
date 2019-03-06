package pl.aem.application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static void main(String[] args){

        String fileName= "Data/objects.data";
        File resultDir = new File("Results");
        if(!resultDir.exists())  resultDir.mkdir();


        FileManager fileManager = new FileManager();
        ArrayList<Point>  pointsList = fileManager.loadProblemData(fileName);
        ArrayList<Double> historyOfMST =new ArrayList<>();


        for (int i=0;i<10;i++) {
//            Algorithm pointOriented = new PointOrientedAlgorithm(pointsList);
//            pointOriented.splitIntoGroups(10,i);
//            historyOfMST.add(pointOriented.getTotalMSTvalue());

            Algorithm groupOriented  = new GroupOrientedAlgorithm(pointsList);
            groupOriented.splitIntoGroups(10,i);
            historyOfMST.add(groupOriented .getTotalMSTvalue());
        }

        System.out.println("\nMax: " +Collections.max(historyOfMST));
        System.out.println("Min: " +Collections.min(historyOfMST));
        System.out.println( "AVG: " +historyOfMST.stream().mapToDouble(val -> val).average().orElse(0.0));

    }



}
