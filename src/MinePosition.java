public class MinePosition {

    private int row;
    private int col;

    public MinePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return "Row: " + row + " Col: " + col;
    }

}
