import java.util.ArrayList;

public class App {

    public static void main(String[] args){

        String fileName= "Data/objects.data";
        FileManager fileManager = new FileManager();
        ArrayList<Point>  pointsList = fileManager.loadProblemData(fileName);

        ComputeManager computeManager= new ComputeManager(pointsList);

        double [][] distanceArray = computeManager.calculateDistanceArray();




        DrawPicture picture = new DrawPicture(pointsList,computeManager.maxXRange(), computeManager.maxYRange());
        picture.draw();

    }
}
