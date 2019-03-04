import java.util.List;

public class ComputeManager {

    public static double[][] calculateDistanceArray(List<Point> pointsList){
        double [][] distanceArray = new double[pointsList.size()][pointsList.size()];

        for (Point a : pointsList){
            for (Point b : pointsList){

                    double distance = Math.sqrt(Math.pow((a.getxCoordinate() - b.getxCoordinate()), 2) + Math.pow((a.getyCoordinate() - b.getyCoordinate()), 2));
                    distanceArray[pointsList.indexOf(a)][pointsList.indexOf(b)]=distance;

        }
        }


        return distanceArray;
    }
}
