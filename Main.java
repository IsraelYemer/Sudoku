/***
 * This runs the Sudoku game
 * @see View
 * @see GameTracker
 */

public class Main {
    /**
     * The main method
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        //display game board
        View view = new View();
        // copy current board into temporary Array
        GameTracker.currentPuzzle();
        //start time
        view.start();
    }
}
