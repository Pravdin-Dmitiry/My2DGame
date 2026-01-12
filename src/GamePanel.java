import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread gameThread;
    private boolean running = false;

    int playerX = 100;
    int playerY = 100;
    int speed = 4;

    boolean up, down, left, right;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

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

    private void update() {
        if (up) playerY -= speed;
        if (down) playerY += speed;
        if (left) playerX -= speed;
        if (right) playerX += speed;

        // Ограничение движения по границам окна
        if (playerX < 0) playerX = 0;
        if (playerX > getWidth() - 40) playerX = getWidth() - 40;
        if (playerY < 0) playerY = 0;
        if (playerY > getHeight() - 40) playerY = getHeight() - 40;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 40, 40); // квадрат игрока
    }

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
    public void keyTyped(KeyEvent e) {}
}
