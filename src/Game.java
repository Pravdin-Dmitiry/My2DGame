import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Игра на Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Установка иконки окна
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        frame.setVisible(true);
        panel.startGame();
    }
}
