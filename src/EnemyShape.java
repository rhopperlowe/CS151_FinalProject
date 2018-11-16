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
            enemyimage = ImageIO.read(new File("resources/enemy.png"));
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
        g2.drawRect(x,y,50,50); // used for debugging collison

    }

    @Override
    public boolean contains(Point2D p) {

        if( (p.getX() >= x && p.getY() >= y)  && (p.getX() <= x+50 && p.getY() <= y+50))
            return true;

        return false;
    }

    @Override
    public void move() {
        x--;

    }


}