import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This displays the puzzle board
 * @see DefaultPuzzle
 * @see SolvedPuzzle
 * @see NewPuzzle
 */
public class View extends JFrame implements ActionListener {

    DefaultPuzzle cells;
    SolvedPuzzle solvedPuzzle;
    NewPuzzle newPuzzle;
    JComboBox<String> comboBox;
    JLabel levelLabel;
    JButton howToPlayButton;
    JButton solveButton;
    JButton newPuzzleButton;
    JLabel timeLabel;
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    //public static String difficultyLevel;

    /***
     * Constructor for class ControllerMainView
     */
    public View() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku");
        this.setSize(650, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setResizable(false);

        howToPlayButton = new JButton(" Rules ");
        howToPlayButton.setPreferredSize(new Dimension(100, 40));
        howToPlayButton.addActionListener(this);

        solveButton = new JButton(" Solve ");
        solveButton.setPreferredSize(new Dimension(100, 40));
        solveButton.addActionListener(this);

        newPuzzleButton = new JButton("New Puzzle");
        newPuzzleButton.setPreferredSize(new Dimension(100, 40));
        newPuzzleButton.addActionListener(this);

        levelLabel = new JLabel("Difficulty:");

        comboBox = new JComboBox<>(new String[]{" Easy ", " Medium ", " Hard "});
        comboBox.setPreferredSize(new Dimension(100, 30));
        comboBox.setSize(100, 50);
        comboBox.setLocation(0, 1);
        comboBox.addActionListener(this);

        timeLabel = new JLabel();
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(75, 75, 200, 50);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        cells = new DefaultPuzzle();

        this.add(howToPlayButton);
        this.add(solveButton);
        this.add(newPuzzleButton);
        this.add(levelLabel);
        this.add(comboBox);
        this.add(timeLabel);
        this.add(cells);
        this.setVisible(true);
    }

    // Puzzle timer
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

        }

    });

    // ActionListeners for buttons and comboBox
    @Override
    public void actionPerformed(ActionEvent e) {

        // This contains game instruction
        if (e.getSource() == howToPlayButton) {
            JOptionPane.showMessageDialog(new JFrame(),
                    """
                            How to play Sudoku

                            The goal of Sudoku is to fill in a 9×9 grid with digits so that each column, row, and
                            3×3 section contain the numbers between 1 to 9.\s
                            At the beginning of the game, the 9×9 grid will have some of the squares filled in.\s
                            Your job is to use logic to fill in the missing digits and complete the grid.\s
                            Don’t forget, a move is incorrect if:

                            Any row contains more than one of the same number from 1 to 9
                            Any column contains more than one of the same number from 1 to 9
                            Any 3×3 grid contains more than one of the same number from 1 to 9

                            Sudoku is a fun puzzle game once you get the hang of it. At the same time,\s
                            learning to play Sudoku can be a bit intimidating for beginners.\s
                            So, if you are a complete beginner, here are a few Sudoku tips that you can use to\s
                            improve your Sudoku skills.

                            Tip 1: Look for rows, columns of 3×3 sections that contain 5 or more numbers.\s
                            Work through the remaining empty cells, trying the numbers that have not been used.\s
                            In many cases, you will find numbers that can only be placed in one position considering\s
                            the other numbers that are already in its row, column, and 3×3 grid.

                            Tip 2: Break the grid up visually into 3 columns and 3 rows. Each large column will\s
                            have 3, 3×3 grids and each row will have 3, 3×3 grids. Now, look for columns or grids\s
                            that have 2 of the same number. Logically, there must be a 3rd copy of the same number\s
                            in the only remaining 9-cell section. Look at each of the remaining 9 positions and see\s
                            if you can find the location of the missing number.

                            Now that you know a little more about Sudoku, play and enjoy this free online game.
                            _______""",
                    "How to play",
                    JOptionPane.PLAIN_MESSAGE);
        }

        // The solve button calls methods in different classes to solve the game
        else if (e.getSource() == solveButton) {
            Helper.solveBoard(GameTracker.currentPuzzle);
            if (Helper.solveBoard(GameTracker.currentPuzzle)) {
                if (cells != null) {
                    this.remove(cells);
                }
                if (solvedPuzzle != null) {
                    this.remove(solvedPuzzle);
                }
                if (newPuzzle != null) {
                    this.remove(newPuzzle);
                }
                solvedPuzzle = new SolvedPuzzle();
                stop();
                this.add(solvedPuzzle);
                SwingUtilities.updateComponentTreeUI(this);
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Sorry! This game is unsolvable. Please try a new puzzle.",
                        "Alert",
                        JOptionPane.PLAIN_MESSAGE);
                PuzzleTracker.difficulty = (String) comboBox.getSelectedItem();

                if (solvedPuzzle != null) {
                    this.remove(solvedPuzzle);
                }
                if (cells != null) {
                    this.remove(cells);
                }
                if (newPuzzle != null) {
                    this.remove(newPuzzle);
                }
                GameTracker.newPuzzle();
                newPuzzle = new NewPuzzle();
                reset();
                start();
                this.add(newPuzzle);
            }
        }

        // The NewPuzzle button generates a new puzzle
        else if (e.getSource() == newPuzzleButton) {
            PuzzleTracker.difficulty = (String) comboBox.getSelectedItem();

            if (solvedPuzzle != null) {
                this.remove(solvedPuzzle);
            }
            if (cells != null) {
                this.remove(cells);
            }
            if (newPuzzle != null) {
                this.remove(newPuzzle);
            }
            GameTracker.newPuzzle();
            newPuzzle = new NewPuzzle();
            reset();
            start();
            this.add(newPuzzle);
            SwingUtilities.updateComponentTreeUI(this);
        }

        // The ComboBox set puzzle difficulty level
        else if (e.getSource() == comboBox) {
            String gameLevel = (String) comboBox.getSelectedItem();
            // Check for a match with enhances switch statement
            switch (Objects.requireNonNull(gameLevel)) {
                case " Easy " -> {
                    PuzzleTracker.difficulty = "Easy";
                    this.remove(cells);
                    if (newPuzzle != null) {
                        this.remove(newPuzzle);
                    }
                    if (solvedPuzzle != null) {
                        this.remove(solvedPuzzle);
                    }
                    cells = new DefaultPuzzle();
                    this.add(cells);
                    reset();
                    start();
                    GameTracker.currentPuzzle();
                    SwingUtilities.updateComponentTreeUI(this);
                }
                case " Medium " -> {
                    PuzzleTracker.difficulty = "Medium";
                    this.remove(cells);
                    if (newPuzzle != null) {
                        this.remove(newPuzzle);
                    }
                    if (solvedPuzzle != null) {
                        this.remove(solvedPuzzle);
                    }
                    cells = new DefaultPuzzle();
                    this.add(cells);
                    reset();
                    start();
                    GameTracker.currentPuzzle();
                    SwingUtilities.updateComponentTreeUI(this);
                }
                case " Hard " -> {
                    PuzzleTracker.difficulty = "Hard";
                    GameTracker.currentPuzzle();
                    if (newPuzzle != null) {
                        this.remove(newPuzzle);
                    }
                    if (solvedPuzzle != null) {
                        this.remove(solvedPuzzle);
                    }
                    this.remove(cells);
                    cells = new DefaultPuzzle();
                    this.add(cells);
                    reset();
                    start();
                    //GameTracker.currentPuzzle(PuzzleLevel.difficulty);
                    SwingUtilities.updateComponentTreeUI(this);
                }
                default -> throw new IllegalStateException("Unexpected value: " + gameLevel);
            }
        }
    }

    // Timer methods
    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }
}



