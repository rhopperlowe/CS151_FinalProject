import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CloudShape implements SceneShape {

    private int x,y;
    private BufferedImage cloudimage;

    public CloudShape(int x, int y){
        this.x = x;
        this.y = y;
        try {
            cloudimage = ImageIO.read(new File("resources/cloud.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(cloudimage,x,y,100,40,null);
    }

    @Override
    public boolean contains(Polygon p) {
        return false;
    }

    @Override
    public void move() {
        x--;
    }
}
