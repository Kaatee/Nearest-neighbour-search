package pl.aem.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RegretHeuristicAlgorithm implements IAlgorithm {

    private double[][] distanceArray ;
    private ArrayList<Point> pointsList ;
    private ArrayList<Group> listOfGroup;

    public RegretHeuristicAlgorithm(double [][] distanceArray, ArrayList<Point> list){
        this.distanceArray=distanceArray;
        this.pointsList=list;
        this.listOfGroup=new ArrayList<>();
    }

    private Point searchForVertex(double[] rowOfFirstVertex){
        Point tmp;
        int tmpIdx=0;
        double tmpValue=rowOfFirstVertex[0];

        for(int i=0;i<rowOfFirstVertex.length;i++){
            if(rowOfFirstVertex[i]>tmpValue){
                tmpIdx=i;
            }
        }
        rowOfFirstVertex[tmpIdx]=0;
        return pointsList.get(tmpIdx);
    }

    private void makeGroups(int numberOfGroups){
        //start vertex
        int idxStartVertex = new Random().nextInt(pointsList.size());
        //first group
        Group firstGroup = new Group(colors[0]);
        firstGroup.addPoint(pointsList.get(idxStartVertex));
        listOfGroup.add(firstGroup);
        double [] rowOfFirstVertex;
        rowOfFirstVertex= distanceArray[idxStartVertex].clone();


        for (int i=1;i<numberOfGroups;i++){
            Group tmpGroup = new Group(colors[i]);
            //search for most distant vertex from start vertex
            Point tmpPoint = searchForVertex(rowOfFirstVertex);
            tmpGroup.addPoint(tmpPoint);
            listOfGroup.add(tmpGroup);
        }


    }

    @Override
    public void splitIntoGroups(int numberOfGroups) {
        makeGroups(10);

        for(Group g : listOfGroup){
            g.setColorPoints();
        }
//        for(Point p : pointsList){
//            p.setColor(colors[new Random().nextInt(colors.length)]);
//        }

    }
}
