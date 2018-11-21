import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyShape implements SceneShape {

    private int x, y;
    private BufferedImage enemyimage;
    private Polygon hitbox;


    public EnemyShape(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            enemyimage = ImageIO.read(new File("resources/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hitbox = new Polygon();
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
        generateHitBox();
//        g2.drawPolygon(hitbox); // used for debugging collison

    }

    @Override
    public boolean contains(Polygon p) {

        return hitbox.intersects(p.getBounds2D());
    }

    @Override
    public void move() {
        x--;
    }

    public void generateHitBox(){
        hitbox.reset();
        hitbox.addPoint(x, y+30);
        hitbox.addPoint(x+30,y+20);
        hitbox.addPoint(x+50, y);
        hitbox.addPoint(x+50, y+40);
        hitbox.addPoint(x+5, y+45);


    }


}