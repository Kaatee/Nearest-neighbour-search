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
                tmpValue=rowOfFirstVertex[i];
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

    private void makeGroupsRandomly(int numberOfGroups){
        for(int i=0;i<10;i++) {
            //start vertex
            int idxStartVertex = new Random().nextInt(pointsList.size());
            //first group
            Group firstGroup = new Group(colors[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
    }

    private void makeGroupsStaticly(int numberOfGroups){
        for(int i=0;i<10;i++) {
            //start vertex
            int idxStartVertex = i*20;
            //first group
            Group firstGroup = new Group(colors[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
    }

    @Override
    public void splitIntoGroups(int numberOfGroups) {
        int addedPoint = 0;
        //makeGroups(10);
        makeGroupsStaticly(10);

        for(Group g : listOfGroup){
            g.setColorPoints();
        }
        //DLA TESTOW
        DrawPicture picture = new DrawPicture(pointsList,255.0, 255.0);
        picture.draw("pomocniczy.png");

        ArrayList<Point> pointsToAdd = new ArrayList(pointsList);
        //dla każdego grupy szukam pokolei punktow ktore moge do niej dodac
        while(addedPoint < pointsList.size()-1) {
            for (int g=0; g<listOfGroup.size();g++) {
                int minPointIdx = 0;
                double minDiffer = Double.MAX_VALUE;
                ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(g).getPointsInGroup());
                double currentLength = listOfGroup.get(g).getMstLen();

                for (int i = 0; i < pointsToAdd.size(); i++) {
                    pointsL.add(pointsToAdd.get(i));
                    MSTFinder newMST = new MSTFinder(pointsL);

                    double newLength = newMST.findMSTlength();
                    double diff = newLength - currentLength;

                    if (diff < minDiffer) {
                        minDiffer = diff;
                        minPointIdx = i;
                    }
                }

                //System.out.println("Dodalem punkt: " + addedPoint + " do grupy: " + listOfGroup.get(g).getGroupColor());
                listOfGroup.get(g).addPoint(pointsToAdd.get(minPointIdx));
                listOfGroup.get(g).setMstLen(listOfGroup.get(g).getMstLen() + minDiffer);
                addedPoint++;
                pointsToAdd.remove(pointsToAdd.get(minPointIdx));
                if(addedPoint>pointsList.size()) break;
            }
        }

        for(Group x : listOfGroup){
            x.setColorPoints();
        }

    }

}
