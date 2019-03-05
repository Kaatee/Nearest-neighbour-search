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

    /**
     * @param numberOfGroups
     * initialize groups based on distance between points
     */
    private void makeGroupsWithMaxDistance(int numberOfGroups){
        //initialize first group
        int idxStartVertex = new Random().nextInt(pointsList.size());
        Group firstGroup = new Group(colors[0]);
        firstGroup.addPoint(pointsList.get(idxStartVertex));
        listOfGroup.add(firstGroup);

        ArrayList<Integer> choosenPointsIndexes = new ArrayList<>();
        choosenPointsIndexes.add(idxStartVertex);

        double[] sum = new double[distanceArray.length];

        for(int i=1;i<numberOfGroups; i++){ //iteruje tak, żeby stworzyć podaną ilosc grup
            int idx; //indeks punktu do dodania do danej grupy
            for(int x=0; x<choosenPointsIndexes.size(); x++){  //iteruje po wszystkich punktach, ktore juz wybralam jako startowe
                for(int j=0; j<distanceArray.length; j++){ //iteruje po wszystkich kol macierzy odleglosci w wierszach z punktami, ktor dodalam
                    if(!choosenPointsIndexes.contains(j))//do sumy konkretnej kolumny dodaje wartosc z danego wiersza
                        sum[j] = distanceArray[choosenPointsIndexes.get(x)][j]; //[wiersz z dodanym punktem][kolumna]
                }
            }
            idx = findMaxValueIdx(sum); //znajduje indeks (czyli nr punktu), ktory ma najwieksza sume odleglosci
            choosenPointsIndexes.add(idx); //do indeksow wybranych punktow dodaje nowy

            for(int z=0;z<sum.length;z++) //zerowanie tablicy sum
                sum[z] = 0;

            Group group = new Group(colors[i]);
            group.addPoint(pointsList.get(idx));
            listOfGroup.add(group);
        }
        System.out.println("Wybralem punkty poczatkowe");
    }

    /**
     * @param sum
     * @return index od max Value in array
     */
    private int findMaxValueIdx(double[] sum){
        int idx = 0;
        double maxValue = Double.MIN_VALUE;
        for(int i=0;i<sum.length;i++){
            if(sum[i]>maxValue){
                idx = i;
                maxValue = sum[i];
            }
        }
        return idx;
    }

    @Override
    public void splitIntoGroups(int numberOfGroups) {
        int addedPoint = 0; //variable which allow to stop alghoritm in proper moment

        //makeGroups(10);
        makeGroupsStaticly(10);
        //makeGroupsWithMaxDistance(10);

        //color points
        for(Group g : listOfGroup){
            g.setColorPoints();
        }

        //FOR TESTS
        DrawPicture picture = new DrawPicture(pointsList,255.0, 255.0);
        picture.draw("pomocniczy.png");

        ArrayList<Point> pointsToAdd = new ArrayList(pointsList);

        //dla każdego grupy szukam pokolei punktow ktore moge do niej dodac
        while(addedPoint < pointsList.size()-1) { //execute until amount of points added into group is equal to number of all points
            for (int g=0; g<listOfGroup.size();g++) { //iterate by groups
                int minPointIdx = 0; //index of point that currently have the best mst lendth to add
                double minDiffer = Double.MAX_VALUE; //value of above len
                ArrayList<Point> pointsL = new ArrayList<>(listOfGroup.get(g).getPointsInGroup()); //points in group which currently tested point added
                double currentLength = listOfGroup.get(g).getMstLen(); //mst len after adding point

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

        double mstLen = 0.0;
        for(Group x : listOfGroup){
            x.setColorPoints();
            mstLen+=x.getMstLen();
        }
        System.out.println("MST: "+mstLen);

    }

}
