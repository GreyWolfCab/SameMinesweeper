import board.Board;
import board.GenerateBoard;
import gui.DrawBoard;

import javax.swing.*;

public class MainApp {

    public static void main(String[] agrs) {

        SwingUtilities.invokeLater(new DrawBoard());

    }

}
