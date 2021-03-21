public class GenerateBoard extends Board {

    private static final char MINE = '*';

    public GenerateBoard(int row, int col) {
        rowDimension = row;
        colDimension = col;
        generateBoard();
        placeMines((int) Math.sqrt(row * col));//number of mines is square root of board size
    }

    /**
     * Generate an empty board as a 2d char matrix
     */
    @Override
    public void generateBoard() {
        board = new char[rowDimension][colDimension];

        //go through all board indices and set each element to be a blank space
        for (int r = 0; r < rowDimension; r++) {//y-axis equals rows

            for (int c = 0; c < colDimension; c++) {//x-axis equals cols

                board[r][c] = ' ';

            }
        }
    }

    /**
     * Randomly place mines on the board
     * @param totalMines the total number of mines to place on the board
     */
    @Override
    public void placeMines(int totalMines) {

        minePositions = new MinePosition[totalMines];

        for (int i = 0; i < totalMines; i++) {//iterate through all mines

            //randomly generate positions for each mine
            int rowPosition = (int) (Math.random() * rowDimension);
            int colPosition = (int) (Math.random() * colDimension);

            //if the position already has a mine at it re-generate the position
            if (getBoardPosition(rowPosition, colPosition) == MINE) {
                i--;
            } else {// otherwise, place the mine at the position
                setBoardPosition(rowPosition, colPosition, MINE);
                minePositions[i] = new MinePosition(rowPosition, colPosition);
            }

        }

    }

    @Override
    public void placeNumbers() {

    }

    /**
     * access the element of the given board position
     * @param row represents the row selected
     * @param col represents the column selected
     * @return the element at the row, column coordinate
     */
    @Override
    public char getBoardPosition(int row, int col) {
        return board[row][col];
    }

    /**
     * change the element of the given board position
     * @param row the selected row
     * @param col the selected column
     * @param value the new value to replace the selected element
     */
    @Override
    public void setBoardPosition(int row, int col, char value) {
        board[row][col] = value;
    }
}
