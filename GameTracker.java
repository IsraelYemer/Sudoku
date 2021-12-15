import java.util.Random;

/**
 * This tracks and modifies the puzzle board
 */
public class GameTracker {

    public static int[][] defaultPuzzle;
    public static int[][] currentPuzzle;
    public static int[][] newPuzzle;
    private static final int GRID_SIZE = 9;

    /**
     * This method creates a duplicate copy of the default puzzle
     * @return 9X9 array of the current puzzle
     */
    public static int[][] currentPuzzle() {
        defaultPuzzle = GameStarter.board();
        currentPuzzle = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < currentPuzzle.length; ++i) {
            for (int j = 0; j < currentPuzzle.length; ++j) {
                currentPuzzle[i][j] = defaultPuzzle[i][j];
            }
        }
        return currentPuzzle;
    }

    /**
     * This method updates the array when user enters a new value
     * @param number user input of int value
     * @param row    holds the numbers 1 through 9. Every number exists in each row and no number repeats
     * @param column holds the numbers 1 through 9. Every number exists in each column and no number repeats
     * @return 9X9 array with updated values
     */
    public static int[][] updatePuzzleBoard(int number, int row, int column) {
        boolean updated = false;
        for (int i = 0; i < currentPuzzle.length; i++) {
            for (int j = 0; j < currentPuzzle.length; j++) {
                if (currentPuzzle[row][column] == currentPuzzle[i][j]) {
                    currentPuzzle[row][column] = number;
                    updated = true;
                    break;
                }
            }
            if (updated) break;
        }
        return currentPuzzle;
    }

    /**
     * This method creates new puzzle
     * @return 9x9 array of a newly generated puzzle
     */
    public static int[][] newPuzzle(){
        int min = 1;
        int max = 9;
        int randInt;
        Random r = new Random();
        newPuzzle = new int[GRID_SIZE][GRID_SIZE];

        switch (PuzzleTracker.difficulty) {
            case " Easy " -> {
                for (int row = 0; row < newPuzzle.length; row++) {
                    for (int column = 0; column < newPuzzle.length; column++) {
                        if (GameStarter.easyPuzzle[row][column] > 0) {
                            while (true) {
                                randInt = r.nextInt(max - min + 1) + min;
                                if (Helper.isNumberValid(newPuzzle, randInt, row, column)) {
                                    newPuzzle[row][column] = randInt;
                                }
                                break;
                            }
                        }
                    }
                }
                currentPuzzle = newPuzzle;
                PuzzleTracker.difficulty = "Easy";
            }
            case " Medium " -> {
                for (int row = 0; row < newPuzzle.length; row++) {
                    for (int column = 0; column < newPuzzle.length; column++) {
                        if (GameStarter.mediumBoard[row][column] > 0) {
                            while (true) {
                                randInt = r.nextInt(max - min + 1) + min;
                                if (Helper.isNumberValid(newPuzzle, randInt, row, column)) {
                                    newPuzzle[row][column] = randInt;
                                }
                                break;
                            }
                        }
                    }
                }
                currentPuzzle = newPuzzle;
                PuzzleTracker.difficulty = "Medium";
            }
            case " Hard " -> {
                for (int row = 0; row < newPuzzle.length; row++) {
                    for (int column = 0; column < newPuzzle.length; column++) {
                        if (GameStarter.hardPuzzle[row][column] > 0) {
                            while (true) {
                                randInt = r.nextInt(max - min + 1) + min;
                                if (Helper.isNumberValid(newPuzzle, randInt, row, column)) {
                                    newPuzzle[row][column] = randInt;
                                }
                                break;
                            }
                        }
                    }
                }
                currentPuzzle = newPuzzle;
                PuzzleTracker.difficulty = "Hard";
            }
            default -> {
                currentPuzzle = newPuzzle;
                PuzzleTracker.difficulty = "Easy";
            }
        }
        return newPuzzle;
    }
}
