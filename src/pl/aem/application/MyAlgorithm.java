package pl.aem.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyAlgorithm implements IAlgorithm {

    private double[][] distanceArray ;
    private ArrayList<Point> pointsList ;
    private ArrayList<Group> listOfGroup;

    public MyAlgorithm(double [][] distanceArray, ArrayList<Point> list){
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
            int idxStartVertex = new Random().nextInt(pointsList.size());
            Group firstGroup = new Group(colors[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
    }

    private void makeGroupsStaticly(int numberOfGroups){
        for(int i=0;i<10;i++) {
            int idxStartVertex = i*20;
            Group firstGroup = new Group(colors[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
    }


    @Override
    public void splitIntoGroups(int numberOfGroups) {

        //makeGroups(10);
        makeGroupsStaticly(10);

        for(Group g : listOfGroup){
            g.setColorPoints();
        }
        //DLA TESTOW
        DrawPicture picture = new DrawPicture(pointsList,255.0, 255.0);
        picture.draw("pomocniczy.png");

        int z=0;

        //dla każdego punktu szukam mu grupy, po dodaniu do której będzie dodawał do jej drzewa rozpinającego minimelną wartość
        for(Point p: pointsList){
            z++;
            int minGroupIdx =0;
            double minDiffer = Double.MAX_VALUE;

            for(int i=0;i<listOfGroup.size();i++){
                double currentLength = listOfGroup.get(i).getMstLen();

                ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(i).getPointsInGroup());
                pointsL.add(p);
                MSTFinder newMST = new MSTFinder(pointsL);
                double newLength = newMST.findMSTlength();

                double diff = newLength - currentLength;
                if(diff < minDiffer){
                    minDiffer = diff;
                    minGroupIdx = i;
                }
            }

            //System.out.println("Dodalem punkt: " + z + " do grupy: "+minGroupIdx);
            listOfGroup.get(minGroupIdx).addPoint(p);
            listOfGroup.get(minGroupIdx).setMstLen(listOfGroup.get(minGroupIdx).getMstLen()+minDiffer);
        }

        double mstLen = 0.0;
        for(Group x : listOfGroup){
            x.setColorPoints();
            mstLen+=x.getMstLen();
        }
        System.out.println("MST: "+mstLen);
    }



}
