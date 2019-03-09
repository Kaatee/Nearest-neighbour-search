package pl.aem.application;

import java.util.ArrayList;
import java.util.Random;



public class GroupInitializer {

    private double[][] distanceArray;
    private ArrayList<Point> pointsList;
    private ArrayList<Group> listOfGroup;

    public GroupInitializer(double[][] distanceArray, ArrayList<Point> list){
        this.distanceArray=distanceArray;
        this.pointsList=list;
        this.listOfGroup=new ArrayList<>();
    }


//    private Point searchForVertex(double[] rowOfFirstVertex){
//        Point tmp;
//        int tmpIdx=0;
//        double tmpValue=rowOfFirstVertex[0];
//
//        for(int i=0;i<rowOfFirstVertex.length;i++){
//            if(rowOfFirstVertex[i]>tmpValue){
//                tmpIdx=i;
//                tmpValue=rowOfFirstVertex[i];
//            }
//        }
//        rowOfFirstVertex[tmpIdx]=0;
//        return pointsList.get(tmpIdx);
//    }
//
//    public void makeGroups(int numberOfGroups){
//        //start vertex
//        int idxStartVertex = new Random().nextInt(pointsList.size());
//        //first group
//        Group firstGroup = new Group(Constants.colors[0]]);
//        firstGroup.addPoint(pointsList.get(idxStartVertex));
//        listOfGroup.add(firstGroup);
//        double [] rowOfFirstVertex;
//        rowOfFirstVertex= distanceArray[idxStartVertex].clone();
//
//
//        for (int i=1;i<numberOfGroups;i++){
//            Group tmpGroup = new Group(colors[i]);
//            //search for most distant vertex from start vertex
//            Point tmpPoint = searchForVertex(rowOfFirstVertex);
//            tmpGroup.addPoint(tmpPoint);
//            listOfGroup.add(tmpGroup);
//        }
//
//
//    }

    public ArrayList<Group>  makeGroupsRandomly(int numberOfGroups){
        ArrayList<Integer> choosen=new ArrayList<>();

        for(int i=0;i<10;i++) {

            //start vertex
            int idxStartVertex = new Random().nextInt(pointsList.size());
            while(choosen.contains(idxStartVertex)){
                 idxStartVertex = new Random().nextInt(pointsList.size());
            }
            choosen.add(idxStartVertex);
            //first group
            Group firstGroup = new Group(Constants.COLORS[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
        return listOfGroup;
    }

    public  ArrayList<Group>  makeGroupsStaticly(int numberOfGroups){
        for(int i=0;i<10;i++) {
            //start vertex
            int idxStartVertex = i*20;
            //first group
            Group firstGroup = new Group(Constants.COLORS[i]);
            firstGroup.addPoint(pointsList.get(idxStartVertex));
            listOfGroup.add(firstGroup);
        }
        return listOfGroup;
    }


    public ArrayList<Group> makeGroupsWithMaxDistance(int numberOfGroups){

        int idxStartVertex = new Random().nextInt(pointsList.size());
        Group firstGroup = new Group(Constants.COLORS[0]);
        firstGroup.addPoint(pointsList.get(idxStartVertex));
        listOfGroup.add(firstGroup);

        ArrayList<Integer> choosenPointsIndexes = new ArrayList<>();
        choosenPointsIndexes.add(idxStartVertex);

        double[] sum = new double[distanceArray.length];

        for(int i=1;i<numberOfGroups; i++){
            int idx;
            for(int x=0; x<choosenPointsIndexes.size(); x++){
                for(int j=0; j<distanceArray.length; j++){
                    if(!choosenPointsIndexes.contains(j))
                        sum[j] = distanceArray[choosenPointsIndexes.get(x)][j];
                }
            }
            idx = findMaxValueIdx(sum);
            choosenPointsIndexes.add(idx);

            for(int z=0;z<sum.length;z++)
                sum[z] = 0;

            Group group = new Group(Constants.COLORS[i]);
            group.addPoint(pointsList.get(idx));
            listOfGroup.add(group);
        }
        System.out.println("Wybralem punkty poczatkowe");

        return listOfGroup;
    }


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

}
