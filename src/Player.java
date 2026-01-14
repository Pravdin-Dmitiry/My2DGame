import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Player {

    public int x, y;
    public int size = 40;
    public int speed = 4;

    private BufferedImage sprite;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            sprite = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/resources/player.png"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(boolean up, boolean down, boolean left, boolean right,
                       int panelWidth, int panelHeight) {

        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > panelWidth - size) x = panelWidth - size;
        if (y > panelHeight - size) y = panelHeight - size;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, size, size, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
