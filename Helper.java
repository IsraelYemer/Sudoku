/**
 * This validates input values and solves the puzzle
 */

public class Helper {
    private static final int GRID_SIZE = 9;

    /***
     * This method checks if a given number exists in a row
     * @param board 2D array containing group of cells that holds an entire set of nine numbers
     * @param number int value for input - to be inserted if valid
     * @param row holds the numbers 1 through 9. Every number exists in each row and no number repeats
     * @return boolean
     */
    public static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if a given number exists in a column
     * @param board 2D array containing group of cells that holds an entire set of nine numbers
     * @param number int value for input  - to be inserted if valid
     * @param column holds the numbers 1 through 9. Every number exists in each column and no number repeats
     * @return boolean
     */
    public static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method looks for a given number in a box (a series of nine cells organized in three mini-rows and three mini-columns within the box)
     * @param board 2D array containing group of cells that holds an entire set of nine numbers
     * @param number int value for input  - to be inserted if valid
     * @param row holds the numbers 1 through 9. Every number exists in each row and no number repeats
     * @param column holds the numbers 1 through 9. Every number exists in each column and no number repeats
     * @return boolean
     */
    public static boolean isNumberInBox(int[][] board, int number, int row, int column) {

        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxColumn; j < boxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method validates a given number input by calling three methods
     * @param board 2D array containing group of cells that holds an entire set of nine numbers
     * @param number int value for input  - to be inserted if valid
     * @param row holds the numbers 1 through 9. Every number exists in each row and no number repeats
     * @param column holds the numbers 1 through 9. Every number exists in each column and no number repeats
     * @return boolean
     */
    public static boolean isNumberValid(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    /**
     * This method solves the puzzle
     * @param board 2D array containing group of cells that holds an entire set of nine numbers
     * @return boolean
     */
    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isNumberValid(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method checks if input is an int
     * @param strNum userInput
     * @return boolean
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        } else if (strNum.equals("0")) {
            return true;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }


    /**
     * This method checks if row is filled
     * @param row 9 cells
     * @return boolean
     */
    public static boolean isRowFilled(int row){

        int filledRows = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            if (GameTracker.currentPuzzle[row][i] > 0) {
                filledRows++;
            }
        }
        if(GRID_SIZE == filledRows){
            return true;
        }
        return false;
    }

    /**
     * This method check if column is filled
     * @param column 9 cells
     * @return boolean
     */
    public static boolean isColumnFilled(int column){

        int filledColumns = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            if (GameTracker.currentPuzzle[i][column] > 0) {
                filledColumns++;
            }
        }
        if(GRID_SIZE == filledColumns){
            return true;
        }
        return false;
    }

    /**
     * This method checks if puzzle is resolved
     * @return boolean
     */
    public static boolean isPuzzleResolved(){
        int filled=0;
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++) {
                if (GameTracker.currentPuzzle[i][j] > 0) {
                    filled++;
                }
            }
        }
        if(filled == GRID_SIZE*GRID_SIZE){
            return true;
        }
        return false;
    }

    /**
     * This method check if input is the last entry
     * @return boolean
     */
    public static boolean isLastEntry(){
        int entryCount = 0;
        for(int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (GameTracker.currentPuzzle[i][j] > 0) {
                    entryCount++;
                }
                if(entryCount == 80){
                    return true;
                }
            }
        }
        return false;
    }
}