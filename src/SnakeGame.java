import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    private ScorePanel scorePanel;

    public static void main(String[] args) {
        new SnakeGame();
    }

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        scorePanel = new ScorePanel(); // 점수 패널 생성
        GamePanel gamePanel = new GamePanel(scorePanel); // 점수 패널을 GamePanel에 전달

        setLayout(new BorderLayout()); // BorderLayout으로 레이아웃 설정
        add(scorePanel, BorderLayout.NORTH); // 점수 패널을 상단에 추가
        add(gamePanel, BorderLayout.CENTER); // 게임 패널을 중앙에 추가

        pack(); // 프레임 크기를 패널에 맞게 자동 조정
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치
        setVisible(true); // 창을 보이게 설정
    }
}
