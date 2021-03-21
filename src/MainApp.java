public class MainApp {

    public static void main(String[] agrs) {

        int boardRow = 10;
        int boardCol = 11;
        Board board = new GenerateBoard(boardRow, boardCol);

        for (int i = 0; i < boardRow; i++) {

            for (int j = 0; j < boardCol; j++) {

                System.out.print(board.getBoardPosition(i, j) + ", ");

            }

            System.out.println();

        }

        for (int i = 0; i < board.minePositions.length; i++) {
            System.out.println(board.minePositions[i]);
        }

    }

}
