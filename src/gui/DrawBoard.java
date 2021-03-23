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
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Minesweeper");//title of the canvas
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//kill the program after closing the frame
        f.add(new GamePanel());//panel that draws the contents of the game board
        f.pack();//draws everything onto the panel
        f.setVisible(true);//makes the frame visible
    }
}
