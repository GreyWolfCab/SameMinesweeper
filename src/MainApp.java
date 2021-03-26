import board.Board;
import board.GenerateBoard;
import gui.DrawBoard;

import javax.swing.*;

public class MainApp {

    public static void main(String[] agrs) {

        int boardRow = 15;
        int boardCol = 11;
        Board board = new GenerateBoard(boardRow, boardCol);

        for (int i = 0; i < boardRow; i++) {

            for (int j = 0; j < boardCol; j++) {

                System.out.print(board.getBoardPosition(i, j) + ", ");

            }

            System.out.println();

        }

        SwingUtilities.invokeLater(new DrawBoard());

    }

}
