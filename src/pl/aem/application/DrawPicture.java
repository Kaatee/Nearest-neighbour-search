package pl.aem.application;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawPicture {
    private ArrayList<Point> pointList ;
    private int maxX;
    private int maxY;

    public DrawPicture (ArrayList<Point> list, double maxX, double maxY ) {//double minY, double maxY){
        pointList = list;
        this.maxX = (int) maxX;
        this.maxY = (int) maxY;
    }


    public void draw() {
        final BufferedImage image = new BufferedImage(maxX+5, maxY+5, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, maxX+5, maxY+5);

        for (Point p : pointList){
            graphics2D.setPaint(p.getColor());
            Ellipse2D.Double circle = new Ellipse2D.Double((int)p.getxCoordinate(), (int)p.getyCoordinate(), 3, 3);
            graphics2D.fill(circle);
        }
        graphics2D.dispose();
        try {
            ImageIO.write(image, "png", new File("result.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}