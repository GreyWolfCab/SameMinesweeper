public abstract class Board {

    protected int rowDimension;//west-east size of board
    protected int colDimension;//north-south size of board

    protected static char[][] board;// includes mine information
    protected MinePosition[] minePositions;// tracks positions of each mine

    public abstract void generateBoard();
    public abstract void placeMines(int totalMines);
    public abstract void placeNumbers();
    public abstract char getBoardPosition(int row, int col);
    public abstract void setBoardPosition(int row, int col, char value);

}
