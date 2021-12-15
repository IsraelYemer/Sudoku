import javax.swing.*;
import java.awt.*;

/**
 * This generates textField cells to hold the value of the solved puzzle
 * @see GameTracker
 */
public class SolvedPuzzle extends JPanel {

    private static final int GRID_SIZE = 9;

    /**
     * Constructor for class SolvedPuzzle
     */
    public SolvedPuzzle(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {

                JTextField textField = new JTextField(String.valueOf(GameTracker.currentPuzzle[row][column]));

                // Add style to the puzzle board
                if ((row < 3 && column < 3) || (row < 3 && column >= 6) || (row >= 6 && column < 3) || (row >= 6 && column >= 6)) {
                    textField.setBackground(new Color(0xddeef3));
                } else if (row >= 3 && row < 6 && column >= 3 && column < 6) {
                    textField.setBackground(new Color(0xddeef3));
                }

                textField.setBorder(BorderFactory.createLineBorder(new Color(0x1796bd)));
                textField.setPreferredSize(new Dimension(65, 65));
                textField.setForeground(new Color(0x34459));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("SansSerif", Font.BOLD, 20));
                textField.setFocusable(false);

                // Add to panel
                panel.add(textField);
                this.add(panel);

            }

        }

    }

}
