package board;

public abstract class Board {

    public static int rowDimension;//west-east size of board
    public static int colDimension;//north-south size of board

    public static char[][] board;// includes mine information
    protected MinePosition[] minePositions;// tracks positions of each mine

    public abstract void generateBoard();
    public abstract void placeMines();
    public abstract void placeNumbers();
    public abstract char getBoardPosition(int row, int col);
    public abstract void setBoardPosition(int row, int col, char value);

}
