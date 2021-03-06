//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class DroneShape extends JComponent implements SceneShape
{
    private int x, y;
    private BufferedImage droneimage;
    private int dy = 1;
    private int dx = 1;
    private Polygon hitbox;

    public DroneShape(int x , int y)
    {
        this.x = x;
        this.y = y;

        try
        {
            droneimage = ImageIO.read(new File("resources/drone.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        hitbox = new Polygon();
        move();
    }

    @Override
    public void draw(Graphics2D g2)
    {
        g2.drawImage(droneimage, x, y, 50, 50, null);
        generateHitBox();
        //g2.drawPolygon(hitbox); // used for debugging collision
    }

    @Override
    public boolean contains(Polygon p)
    {
        return hitbox.intersects(p.getBounds2D());
    }

    public void setDy(int dy)
    {
        this.dy = dy;
    }
    
    public void setDx(int dx)
    {
    	this.dx = dx;
    }

    public void moveX()
    {
		x += dx;
	}

	public void moveY()
    {
		y += dy;
	}
	
	public int getX()
    {
		return x;
	}
	
	public int getY()
    {
		return y;
	}
	
	@Override
	public void move()
    {
		if (dx != 0) 
        {
            this.moveX();
        }
		if (dy != 0)
        {
            this.moveY();
        }
	}

	public void generateHitBox()
    {
        hitbox.reset();
        hitbox.addPoint(x, y+8);
        hitbox.addPoint(x+50,y+8);
        hitbox.addPoint(x+40,y+25);
        hitbox.addPoint(x+45,y+45);
        hitbox.addPoint(x+25,y+30);
        hitbox.addPoint(x+10,y+45);
        hitbox.addPoint(x+10,y+30);
    }

    public Polygon getHitbox()
    {
        return hitbox;
    }
}
