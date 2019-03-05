package pl.aem.application;

import java.awt.*;

public interface IAlgorithm {
    void splitIntoGroups(int numberOfGroups);
    Color[] colors = {
            Color.RED , Color.GRAY, Color.BLUE, Color.CYAN , Color.GREEN , Color.MAGENTA ,
            Color.PINK , Color.YELLOW , Color.ORANGE , Color.BLACK
    };
}
