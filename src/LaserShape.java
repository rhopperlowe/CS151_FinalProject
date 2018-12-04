//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LaserShape extends JComponent implements SceneShape
{

	private int x, y;
	private BufferedImage laserImage;
	private Polygon hitbox;
	
	private int width = 10;
	private int height = 5;
	private int dx = 5;

	
	public LaserShape(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		try
		{
			laserImage = ImageIO.read(new File("resources/Laser.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		hitbox = new Polygon();
		move();
	}

	public LaserShape()
	{
		hitbox = new Polygon();
	}
	
	public void generateHitbox()
	{
		hitbox.reset();
		hitbox.addPoint(x, y);
		hitbox.addPoint(x, y + height);
		hitbox.addPoint(x + width, y);
		hitbox.addPoint(x + width, y + height);
	}

	public int getX()
	{
		return x;
	}
	
	public Polygon getHitbox()
	{
		return hitbox;
	}
	
	@Override
	public void draw(Graphics2D g2)
	{
		g2.drawImage(laserImage, x, y, 20, 10, null);
		generateHitbox();
	}

	@Override
	public boolean contains(Polygon p)
	{
		return hitbox.intersects(p.getBounds2D());
	}
	
	@Override
	public void move()
	{
		if(x < 500)
		{
			x += dx;
		}
	}

}
