import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int UNIT_NUM = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private int[] x = new int[UNIT_NUM];
    private int[] y = new int[UNIT_NUM];
    private int appleX = 0;
    private int appleY = 0;
    private int snakeNum = 3;
    private final int DELAY = 150;
    private char direction = 'R';
    private Timer timer;
    private boolean running;
    private ScorePanel scorePanel;
    private JButton restartButton;
    private JButton exitButton;

    public GamePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new myKeyAdapter());

        setLayout(null);
        restartButton = new JButton("Restart");
        restartButton.setBounds(90, 300, 100, 40);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        exitButton = new JButton("Exit");
        exitButton.setBounds(210, 300, 100, 40);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(restartButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if(running) {
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snakeNum; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.YELLOW);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
        } else {
            gameOver(g);
        }
    }

private void move() {
        for (int i = snakeNum; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
        }
    }

    public void startGame() {
        snakeNum = 3;
        scorePanel.updateScore(0);
        direction = 'R';
        running = true;
        for(int i=0; i<snakeNum; i++) {
            x[i] = -UNIT_SIZE;
            y[i] = -UNIT_SIZE;
        }
        x[0] = HEIGHT / 2;
        y[0] = 0;
        randomApple();
        if(timer != null) {
            timer.stop();
        }
        timer = new Timer(DELAY, this);
        timer.start();
        restartButton.setVisible(false);
        exitButton.setVisible(false);
        scorePanel.setVisible(true);
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkCollision();
            checkApple();
        }
        repaint();
    }

    private void checkApple() {
        if(x[0] == appleX && y[0] == appleY) {
            snakeNum++;
            scorePanel.updateScore(snakeNum-3);
            randomApple();
        }
    }

    private void randomApple() {
        appleX = (int) (Math.random() * (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    private void checkCollision() {
        if(x[0] >= WIDTH || x[0] < 0 || y[0] >= HEIGHT || y[0] < 0) {
            running = false;
        }

        for(int i=snakeNum; i>0; i--) {
            if (x[i] == x[0] && y[i] == y[0]) {
                running = false;
                break;
            }
        }

        if(!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
        g.drawString("Score : " + (snakeNum-3), (WIDTH - metrics.stringWidth("Score :" + (snakeNum-3))) / 2, HEIGHT / 2 + 40);

        restartButton.setVisible(true);
        exitButton.setVisible(true);
        scorePanel.setVisible(false);
    }

    private class myKeyAdapter extends KeyAdapter {
       @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
            }
        }
    }
}
