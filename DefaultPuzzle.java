import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This generates textField cells to hold the value of the initial puzzle
 *
 * @see GameStarter
 */
public class DefaultPuzzle extends JPanel {

    private static final int GRID_SIZE = 9;


    /**
     * Constructor for class DefaultPuzzle
     */
    public DefaultPuzzle() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        // Place 9x9  textField into the panel and the textFields contain the puzzle values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {

                JTextField textField = new JTextField(String.valueOf(GameStarter.board()[row][column]));

                int finalRow = row;
                int finalColumn = column;

                // Add style to the puzzle board
                if ((row < 3 && column < 3) || (row < 3 && column >= 6) || (row >= 6 && column < 3) || (row >= 6 && column >= 6)) {
                    textField.setBackground(new Color(221, 238, 243));
                } else if (row >= 3 && row < 6 && column >= 3 && column < 6) {
                    textField.setBackground(new Color(221, 238, 243));
                }

                textField.setBorder(BorderFactory.createLineBorder(new Color(23, 150, 189)));
                textField.setPreferredSize(new Dimension(65, 65));
                textField.setForeground(new Color(3, 68, 89));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("SansSerif", Font.BOLD, 20));
                textField.setFocusable(true);

                // Limit the JTextField to 1 input
                if (textField.getText().equals(String.valueOf(0))) {
                    textField.setDocument(new JTextFieldLimit(1));
                }
                // Set focusable to false for givens
                if (!(textField.getText().equals(""))) {
                    textField.setFocusable(false);
                }

                // Add to panel
                panel.add(textField);
                this.add(panel);

                textField.addKeyListener(new KeyAdapter() {

                    public void keyReleased(KeyEvent e) {

                        if ((Helper.isLastEntry()) && ((Helper.isNumberValid(GameTracker.currentPuzzle, Integer.parseInt(textField.getText()), finalRow, finalColumn)))) {

                           View view = new View(); 
                           view.stop();
                            Object[] options = {"Quit","Try New Puzzle"};

                            int result = JOptionPane.showOptionDialog(null,  "Congratulations! You've solved this puzzle", "Congratulations",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, options, null);
                            if (result == 1) {
                                textField.setFocusable(false);
                            }
                            else{
                                System.exit(0);
                            }

                        }
                    }

                });

                // Listen for changes in the JTextField
                textField.addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        textField.setBorder(BorderFactory.createLineBorder(new Color(3, 68, 89)));
                        //textField.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        textField.setBorder(BorderFactory.createLineBorder(new Color(3, 68, 89)));
                        String userInput = (textField.getText());
                        // Check if input is numeric
                        if ((!textField.getText().equals("")) && (Helper.isNumeric(textField.getText()))) {
                            textField.setText("");
                            JOptionPane.showMessageDialog(new JFrame(), "Invalid input! Please enter only number 1-9", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        // Check if input is not empty
                        else if ((!textField.getText().equals(""))) {

                            // If number is not valid set textField color to red
                            if (!Helper.isNumberValid(GameTracker.currentPuzzle, Integer.parseInt(userInput), finalRow, finalColumn)) {
                                textField.setBackground(new Color(238, 176, 181));
                            }
                            // If number is valid set textField color to green
                            else if (Helper.isNumberValid(GameTracker.currentPuzzle, Integer.parseInt(userInput), finalRow, finalColumn)) {
                                textField.setBackground(new Color(178, 241, 175));
                                GameTracker.updatePuzzleBoard(Integer.parseInt(userInput), finalRow, finalColumn);
                            }

                        }

                    }

                });
            }

        }

    }

}

