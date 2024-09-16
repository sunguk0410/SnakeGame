import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel(SnakeGame snakeGame) {
        setLayout(null);

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBounds(50, 50, 400, 100);

        JButton button = new JButton("Game Start");
        button.setBounds(100, 200, 200, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeGame.showGamePanel();
            }
        });
        add(button);

        add(titleLabel);
    }
}
