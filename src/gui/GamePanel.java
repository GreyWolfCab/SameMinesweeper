package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private GameSquare gameSquare = new GameSquare('7');

    public GamePanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));//draws a border around the panel

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

    }

    /**
     * sets the default size of the panel
     * @return a panel with the given dimensions
     */
    public Dimension getPreferredSize() {
        return new Dimension(300, 250);
    }

    /**
     * where custom painting occurs
     * @param g allows the drawing of certain shapes and images
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("This is custom drawn", 10, 20);
        gameSquare.paintSquare(g);
    }

    /**
     * redraws the square according to the new coordinates
     * @param x x-axis value of the square
     * @param y y-axis value of the square
     */
    private void moveSquare(int x, int y) {

        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = gameSquare.getX();
        final int CURR_Y = gameSquare.getY();
        final int CURR_W = gameSquare.getWidth();
        final int CURR_H = gameSquare.getHeight();
        final int OFFSET = 1;

        if ((CURR_X != x) || (CURR_Y != y)) {
            repaint(CURR_X, CURR_Y, CURR_W+OFFSET, CURR_H+OFFSET);//paints over the previous square
            gameSquare.setX(x);//updates square position
            gameSquare.setY(y);
            //paints the new square in the new position
            repaint(gameSquare.getX(), gameSquare.getY(), gameSquare.getWidth()+OFFSET, gameSquare.getHeight()+OFFSET);
            //swing handles both repaint calls in one operation
        }

    }

}
