import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    private JPanel totalPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public static void main(String[] args) {
        new SnakeGame();
    }

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();

        mainPanel = new JPanel(new BorderLayout());

        totalPanel = new JPanel(cardLayout);

        MenuPanel menuPanel = new MenuPanel(this);
        scorePanel = new ScorePanel();
        gamePanel = new GamePanel(scorePanel);

        mainPanel.add(scorePanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        totalPanel.add(menuPanel, "Menu");
        totalPanel.add(mainPanel, "Game");

        add(totalPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        cardLayout.show(totalPanel, "Menu");
    }

    public void showGamePanel() {
        cardLayout.show(totalPanel, "Game");
        gamePanel.startGame();
    }
}
