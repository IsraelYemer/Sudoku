/**
 * This contains arrays of default puzzle boards
 * @see PuzzleTracker
 */
public class GameStarter {
    public static int[][] easyPuzzle;
    public static int[][] hardPuzzle;
    public static int[][] mediumBoard;

    /**
     * This method generates puzzle based on user difficulty level selection
     * @return 9x9 board
     */
    public static int[][] board() {

        switch (PuzzleTracker.difficulty) {
            case "Easy" -> {
                easyPuzzle = new int[][]{
                        {5, 0, 4, 0, 8, 7, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 5, 0},
                        {6, 0, 7, 3, 0, 1, 0, 8, 4},
                        {0, 0, 1, 0, 9, 0, 3, 0, 0},
                        {0, 3, 6, 8, 0, 0, 4, 0, 0},
                        {0, 8, 0, 7, 0, 4, 0, 6, 1},
                        {3, 0, 9, 0, 0, 8, 0, 4, 0},
                        {0, 4, 8, 5, 7, 0, 1, 0, 0},
                        {1, 0, 0, 0, 0, 6, 0, 0, 0}
                };
                return easyPuzzle;
            }
            case "Medium" -> {
                mediumBoard = new int[][]{
                        {0, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 3, 6, 0, 0, 0, 0, 0},
                        {0, 7, 0, 0, 9, 0, 2, 0, 0},
                        {0, 5, 0, 0, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 4, 5, 7, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 3, 0},
                        {0, 0, 1, 0, 0, 0, 0, 6, 8},
                        {0, 0, 8, 5, 0, 0, 0, 1, 0},
                        {0, 9, 0, 0, 0, 0, 4, 0, 0}
                };
                return mediumBoard;
            }
            case "Hard" -> {
                hardPuzzle = new int[][]{
                        {0, 0, 3, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 6, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 9, 0, 2, 0, 0},
                        {0, 5, 0, 0, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 4, 5, 7, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 3, 0},
                        {0, 0, 1, 0, 0, 0, 0, 6, 8},
                        {0, 0, 8, 5, 0, 0, 0, 1, 0},
                        {0, 9, 0, 0, 0, 0, 4, 0, 0}
                };
                return hardPuzzle;
            }
            default -> {
                return easyPuzzle;
            }
        }
    }
}
