package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public enum Squares {

    ZERO("0.png"),
    ONE("1.png"),
    TWO("2.png"),
    THREE("3.png"),
    FOUR("4.png"),
    FIVE("5.png"),
    SIX("6.png"),
    SEVEN("7.png"),
    EIGHT("8.png"),
    EXPLOSION("ExploCell.png"),
    FLAG("FlaggedCell.png"),
    UNOPEN("UnopenedCell.png");

    private BufferedImage image;

    private Squares(String fileName) {

        //read images without specifying a path
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream iStream = classLoader.getResourceAsStream(fileName);
        try {
            image = ImageIO.read(iStream);
        } catch(IOException e) {
            image = null;
        }

    }

    public BufferedImage getImage() {
        return image;
    }

}
