public abstract class Board {

    protected int XDimension;
    protected int YDimension;

    protected static char[][] board;

    public abstract void generateBoard();
    public abstract void placeMines(int totalMines);
    public abstract void placeNumbers();
    public abstract char accessBoard(int x, int y);

}
