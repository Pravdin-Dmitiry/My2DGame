import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private boolean running = false;

    // ===== ИГРОВЫЕ ОБЪЕКТЫ =====
    private final Player player;
    private final Enemy enemy;

    // ===== ФОН =====
    private BufferedImage background;

    // ===== УПРАВЛЕНИЕ =====
    private boolean up, down, left, right;

    // ===== КОНСТРУКТОР =====
    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);

        // Создаём игрока и врага
        player = new Player(100, 100);
        enemy = new Enemy(500, 300);

        // Загружаем фон
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/resources/background.png"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== ЗАПУСК ИГРЫ =====
    public void startGame() {
        running = true;
        // ===== ИГРОВОЙ ПОТОК =====
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    // ===== ИГРОВОЙ ЦИКЛ =====
    @Override
    public void run() {
        while (running) {
            update();
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ===== ОБНОВЛЕНИЕ ЛОГИКИ =====
    private void update() {
        player.update(up, down, left, right, getWidth(), getHeight());
        enemy.update(player);

        // Проверка столкновения
        if (player.getBounds().intersects(enemy.getBounds())) {
            running = false;
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
    }

    // ===== ОТРИСОВКА =====
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ФОН
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }

        // ИГРОВЫЕ ОБЪЕКТЫ
        player.draw(g);
        enemy.draw(g);
    }

    // ===== КЛАВИАТУРА =====
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // не используется
    }
}
