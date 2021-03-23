package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSquare {

    private int xPos = 50;
    private int yPos = 50;
    private int width = 48;
    private int height = 48;
    private BufferedImage image;

    public GameSquare(char element) {

        setImage(element);

    }

    public void setImage(char element) {

        switch (element) {
            case '0': image = Squares.ZERO.getImage();
                break;
            case '1': image = Squares.ONE.getImage();
                break;
            case '2': image = Squares.TWO.getImage();
                break;
            case '3': image = Squares.THREE.getImage();
                break;
            case '4': image = Squares.FOUR.getImage();
                break;
            case '5': image = Squares.FIVE.getImage();
                break;
            case '6': image = Squares.SIX.getImage();
                break;
            case '7': image = Squares.SEVEN.getImage();
                break;
            case '8': image = Squares.EIGHT.getImage();
                break;
            case '*': image = Squares.EXPLOSION.getImage();
                break;
            case '!': image = Squares.FLAG.getImage();
                break;
            default: image = Squares.UNOPEN.getImage();
                break;
        }

    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void paintSquare(Graphics g) {
//        g.setColor(Color.RED);
//        g.fillRect(xPos, yPos, width, height);
        g.drawImage(image, xPos, yPos, null);
    }
}
