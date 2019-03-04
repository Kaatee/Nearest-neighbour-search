package pl.aem.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManager {

    public ArrayList<Point> loadProblemData(String fileName) {
        BufferedReader br;
        ArrayList<Point>  pointsList= new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line ;
            while ((line = br.readLine())!=null){
                String[] coordinate =line.split(" ");
                Point tmp = new Point(Double.parseDouble(coordinate[0]),Double.parseDouble(coordinate[1]));
                pointsList.add(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pointsList;
    }
}


