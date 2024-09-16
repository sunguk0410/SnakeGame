import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel{
    private JLabel scorePanel;

    public ScorePanel() {
        setPreferredSize(new Dimension(400, 60));
        setBackground(Color.WHITE);

        scorePanel = new JLabel("Score : 0", JLabel.CENTER);
        setForeground(Color.BLUE);

        scorePanel.setFont(new Font("Arial", Font.BOLD, 40));

        add(scorePanel);
    }

    public void updateScore(int score) {
        scorePanel.setText("Score : " + score);
    }
}
