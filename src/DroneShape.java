import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DroneShape implements SceneShape {

    private int x, y;
    private BufferedImage droneimage;
    private int dy = 1;
    private int dx = 1;


    public DroneShape(int x , int y){
        this.x = x;
        this.y = y;
        try {
            droneimage = ImageIO.read(new File("resources/drone.png"));
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
        g2.drawImage(droneimage, x, y, 50, 50, null);
        g2.drawRect(x, y, 45, 45); // used for debugging collison
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }
    
    
    public void setDy(int dy) {
        this.dy = dy;
    }
    
    public void setDx(int dx) {
    	this.dx = dx;
    }

    public void moveX() {
		x += dx * 4;
	}

	public void moveY() {
		y += dy * 4;
	}
	
	@Override
	public void move() {
		if (dx != 0) 
			this.moveX();
		if (dy != 0)
			this.moveY();
	}
}
