package pl.aem.application;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
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


    private BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }


    public void draw(String fileName) {
        final BufferedImage image = new BufferedImage(maxX+5, maxY+5, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, maxX+5, maxY+5);

        for (Point p : pointList){
            graphics2D.setPaint(p.getColor());
            Ellipse2D.Double circle = new Ellipse2D.Double((int)p.getxCoordinate(), (int)p.getyCoordinate(), 3, 3);
            //System.out.println("Narysowalem" + p.getColor());
            graphics2D.fill(circle);
        }
        graphics2D.dispose();
        try {
            //ImageIO.write(image, "png", new File("resultNORESCALE.png"));
            ImageIO.write(resizeImage(image,2000,2000), "png", new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
