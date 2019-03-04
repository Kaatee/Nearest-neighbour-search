import java.util.ArrayList;

public class App {

    public static void main(String[] args){

        String fileName= "Data/objects.data";
        FileManager fileManager = new FileManager();
        ArrayList<Point>  pointsList = fileManager.loadProblemData(fileName);


        double [][] distanceArray = ComputeManager.calculateDistanceArray(pointsList);



    }
}
