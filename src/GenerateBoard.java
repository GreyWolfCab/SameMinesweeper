public class GenerateBoard extends Board {

    public GenerateBoard(int x, int y) {
        XDimension = x;
        YDimension = y;
        generateBoard();
    }

    @Override
    public void generateBoard() {
        board = new char[XDimension][YDimension];

        for (int y = 0; y < YDimension; y++) {

            for (int x = 0; x < XDimension; x++) {

                board[y][x] = ' ';

            }
        }
    }

    @Override
    public void placeMines(int totalMines) {

    }

    @Override
    public void placeNumbers() {

    }

    @Override
    public char accessBoard(int x, int y) {
        return board[x][y];
    }
}
