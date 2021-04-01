package board;

public abstract class Board {

    protected static int rowDimension;//west-east size of board
    protected static int colDimension;//north-south size of board

    protected static char[][] board;// includes mine information
    public static MinePosition[] minePositions;// tracks positions of each mine

    public abstract void generateBoard();
    public abstract void placeMines();
    public abstract void placeNumbers();
    public abstract char getBoardPosition(int row, int col);
    public abstract void setBoardPosition(int row, int col, char value);
    public int getRowDimension() { return rowDimension; }
    public int getColDimension() { return colDimension; }

}
