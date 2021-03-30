package gui;

import javax.swing.*;

public class DrawBoard implements Runnable {

    /**
     * run is executed every time a new DrawBoard thread is created
     */
    @Override
    public void run() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        JFrame f = new JFrame("Minesweeper");//title of the canvas
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kill the program after closing the frame
        f.add(new GamePanel());//panel that draws the contents of the game board
        f.pack();//draws everything onto the panel
        //after all components have been added to the jFrame
        f.setLocationRelativeTo(null);//get window position to be center of screen
        f.setVisible(true);//makes the frame visible
    }
}
