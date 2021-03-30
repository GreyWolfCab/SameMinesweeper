package board;

public class GenerateBoard extends Board {

    private static final char MINE = '*';
    private int totalMines;// = (rowDimension + colDimension) * ln(Max(rowDimension, colDimension)) * minePercentage

    public GenerateBoard(int row, int col) {
        rowDimension = row;
        colDimension = col;
        totalMines = calculateMineCount(row, col);
        System.out.println(totalMines);
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

                board[r][c] = '0';

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

        for (int i = 0; i < minePositions.length; i++) {
            System.out.println(minePositions[i]);
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

                        //check if c is < 0 or > maxCol AND if r & c are not the origin
                        if ((c >= 0 && c < colDimension) && (r != originRow || c != originCol)) {
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

    /**
     * determine the difficulty by generating a certain number of mines based on the size of the board
     * @param row the vertical direction of the board
     * @param col the horizontal direction of the board
     * @return the total number of mines to place on the board
     */
    private int calculateMineCount(int row, int col) {

        // = (rowDimension + colDimension) * ln(Max(rowDimension, colDimension)) * minePercentage
        //if row + col <&> 20 : 0.26; >= 32 : 0.46; >= 46 : 0.64
        //16x16 = 40 mines
        //9x9 = 10 mines
        //30x16 = 99 mines

        int maxValue = Math.max(row, col);//max value between board shape
        int sum = row + col;//sum of board shape values
        double minePercentage;//difficulty percentage according to board size

        if (sum < 32) {//smaller boards are less difficult
            minePercentage = 0.26;
        } else if (sum <= 46) {//medium boards retain more mines
            minePercentage = 0.46;
        } else {
            minePercentage = 0.64;//large boards have tons of mines
        }

        return (int) (sum * Math.log(maxValue) * minePercentage);//guess what this does...

    }
}
