package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CloudComponent implements SceneShape {

    private int x,y;
    private BufferedImage cloudimage;

    public CloudComponent(int x, int y){
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
        g2.drawImage(cloudimage,x,y,20,40,null);
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
