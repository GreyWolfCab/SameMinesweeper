package gui;

import board.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSquare {

    public static int width = 48;
    public static int height = 48;

    private int row;
    private int col;
    private int xPos;
    private int yPos;
    private BufferedImage image;

    public GameSquare(int row, int col, char element) {

        this.row = row;
        this.col = col;
        if (col != 0) {
            xPos = col * (width / Board.colDimension);
        } else {
            xPos = 0;
        }

        if (row != 0) {
            yPos = row * (height / Board.rowDimension);
        } else {
            yPos = 0;
        }

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

    public void setRow(int row) { this.row = row; }

    public void setCol(int col) { this.col = col; }

    public int getRow() { return row; }

    public int getCol() { return col; }

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

    public static void setWidth(int width) {
        GameSquare.width = width;
    }

    public static void setHeight(int height) {
        GameSquare.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void paintSquare(Graphics g) {
        g.drawImage(image, xPos, yPos, width, height, null);
    }
}
