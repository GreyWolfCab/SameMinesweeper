package gui;

import board.Board;
import board.GenerateBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private GameSquare[][] gameSquares = new GameSquare[Board.rowDimension][Board.colDimension];

    public GamePanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));//draws a border around the panel

        addMouseListener(new MouseAdapter() {
            //listen for what square on the grid has been pressed
            public void mousePressed(MouseEvent e) {
                int selectedRow = e.getY() / gameSquares[0][0].getHeight();
                int selectedCol = e.getX() / gameSquares[0][0].getWidth();

                //if click on 0, reveal all adjacent zeroes and any numbers touching a zero
                if (Board.board[selectedRow][selectedCol] == '0') {
                    //reveal all connected zero squares
                    revealSurrounding(selectedRow, selectedCol);
                } else {
                    revealSquare(selectedRow, selectedCol);//update the game square with its true element
                    drawRevealedSquare(selectedRow, selectedCol);//draw the updated square element
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            /**
             * TODO: is not a pixel perfect scaler, figure out a way to center the board within the panel
             * Anytime the size of the app is changed, modify each game square to fill in the window
             * @param e the component that has been resized
             */
            @Override
            public void componentResized(ComponentEvent e) {
                //scale game squares
                Dimension currentSize = getCurrentSize();//gets the new size

                //update every game square with a new scale
                for (int r = 0; r < gameSquares.length; r++) {

                    for (int c = 0; c < gameSquares[r].length; c++) {

                        drawResizedBoard(r, c, currentSize);//redraw every square based on the new size

                    }

                }

                //sets the new size for every gamesquare
                GameSquare.setWidth(currentSize.width / gameSquares[0].length);
                GameSquare.setHeight(currentSize.height / gameSquares.length);

            }
        });

        initializeBoard();

    }

    private void revealSurrounding(int selectedRow, int selectedCol) {

        for (int r = selectedRow - 1; r <= selectedRow+1; r++) {//iterate through the above and below row

            //prevent r from being < 0 or > maxRow (avoid index out of bounds)
            if (r >= 0 && r < Board.rowDimension) {

                //iterate through the left and right column
                for (int c = selectedCol - 1; c <= selectedCol+1; c++) {

                    //check if c is < 0 or > maxCol
                    if (c >= 0 && c < Board.colDimension) {

                        if (gameSquares[r][c].getElement() == ' ') {//avoid duplicate checks on squares

                            if (Board.board[r][c] == '0') {//if the square is zero
                                revealSquare(r, c);//update the game square with its true element
                                drawRevealedSquare(r, c);//draw the updated square element
                                revealSurrounding(r, c);//reveal the surrounding squares
                            } else {
                                revealSquare(r, c);//update the game square with its true element
                                drawRevealedSquare(r, c);//draw the updated square element
                            }

                        }

                    }

                }

            }

        }

    }

    private void revealSquare(int selectedRow, int selectedCol) {
        //update square with revealed element
        gameSquares[selectedRow][selectedCol].setImage(Board.board[selectedRow][selectedCol]);
        gameSquares[selectedRow][selectedCol].setElement(Board.board[selectedRow][selectedCol]);
    }

    private void drawRevealedSquare(int selectedRow, int selectedCol) {
        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = gameSquares[selectedRow][selectedCol].getX();
        final int CURR_Y = gameSquares[selectedRow][selectedCol].getY();
        final int CURR_W = gameSquares[selectedRow][selectedCol].getWidth();
        final int CURR_H = gameSquares[selectedRow][selectedCol].getHeight();
        final int OFFSET = 1;//what does this do?
        repaint(CURR_X, CURR_Y, CURR_W+OFFSET, CURR_H+OFFSET);//paints over the previous square
    }

    /**
     * where custom painting occurs
     * @param g allows the drawing of certain shapes and images
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("This is custom drawn", 10, 20);
        repaintBoard(g);

    }

    /**
     * create the player board that responds to player input
     */
    private void initializeBoard() {

        //iterate through every game square
        for (int r = 0; r < gameSquares.length; r++) {

            for (int c = 0; c < gameSquares[r].length; c++) {

                gameSquares[r][c] = new GameSquare(r, c, ' ');//set every square to be unopend by default

            }

        }
    }

    /**
     * calls every game square to paint itself
     * @param g object used to paint the game square to the panel
     */
    private void repaintBoard(Graphics g) {

        for (GameSquare[] gameSquare : gameSquares) {

            for (GameSquare square : gameSquare) {

                square.paintSquare(g);

            }

        }

    }

    private void drawResizedBoard(int r, int c, Dimension currentSize) {

        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = gameSquares[r][c].getX();
        final int CURR_Y = gameSquares[r][c].getY();
        final int CURR_W = gameSquares[r][c].getWidth();
        final int CURR_H = gameSquares[r][c].getHeight();
        final int OFFSET = 1;//what does this do?
        repaint(CURR_X, CURR_Y, CURR_W+OFFSET, CURR_H+OFFSET);//paints over the previous square
        gameSquares[r][c].setX(c * (currentSize.width / gameSquares[r].length) );//updates square position
        gameSquares[r][c].setY(r * (currentSize.height / gameSquares.length));
        //paints the new square in the new position
        repaint(gameSquares[r][c].getX(), gameSquares[r][c].getY(),
                (currentSize.width / gameSquares[0].length)+OFFSET,
                (currentSize.height / gameSquares.length)+OFFSET);
        //swing handles both repaint calls in one operation

    }

    /**
     * sets the default size of the panel, initially the app window takes up a small amount of screen space
     * @return a panel with the given dimensions
     */
    public Dimension getPreferredSize() {
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5);
        return new Dimension(width, height);
    }

    public Dimension getCurrentSize() {
        return this.getSize();
    }

}
