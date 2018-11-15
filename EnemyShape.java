import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyShape implements SceneShape {

    private int x, y;
    private BufferedImage enemyimage;


    public EnemyShape(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            enemyimage = ImageIO.read(new File("/home/seb/IdeaProjects/CS151SebastianMora/CS151SebastianMora/Drone/resources/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(enemyimage, x, y, 50, 50, null);

    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public void move() {
        x--;

    }


}