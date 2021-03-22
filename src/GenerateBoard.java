public class GenerateBoard extends Board {

    private static final char MINE = '*';
    private int totalMines;

    public GenerateBoard(int row, int col) {
        rowDimension = row;
        colDimension = col;
        totalMines = (int) Math.sqrt(row * col);
        generateBoard();
        placeMines();//number of mines is square root of board size
        placeNumbers();
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
     */
    @Override
    public void placeMines() {

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

        for (int i = 0; i < totalMines; i++) {
            int originRow = minePositions[i].getRow();
            int originCol = minePositions[i].getCol();

            for (int r = originRow - 1; r < originRow + 2; r++) {//iterate through 3x3 surrounding rows

                //prevent r from being < 0 or > maxRow (avoid index out of bounds)
                if (r >= 0 && r < rowDimension) {

                    for (int c = originCol - 1; c < originCol + 2; c++) {//iterate through 3x3 surrounding cols

                        //check if c is < 0 or > maxCol
                        if ((c >= 0 && c < colDimension) && (r != originRow || c != originCol)) {
                            //check if r & c are the origin
                            char element = getBoardPosition(r, c);
                            if (element != '*') {//avoid overwriting mines
                                if (element == ' ') {//if blank space
                                    setBoardPosition(r, c, '1');//intial mine detected
                                } else {//multiple mines nearby
                                    setBoardPosition(r, c, ++element);//increment mine indicator (maximum of 9)
                                }
                            }
                        }

                    }

                }

            }

        }

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
