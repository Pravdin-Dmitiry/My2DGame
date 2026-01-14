import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy {

    public int x, y;
    public int size = 30;
    public int speed = 2;

    private BufferedImage sprite;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            sprite = ImageIO.read(
                    getClass().getResource("/resources/enemy.png")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Player player) {
        if (player.x < x) x -= speed;
        if (player.x > x) x += speed;
        if (player.y < y) y -= speed;
        if (player.y > y) y += speed;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, size, size, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
