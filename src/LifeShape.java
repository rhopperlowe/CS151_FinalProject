import javax.imageio.ImageIO;
import javax.swing.Icon;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LifeShape implements Icon {
	private int						width, height;
    private BufferedImage 			lifeImage;
    
    public LifeShape(int width) {
        
        this.width = width;
        height = width;
        
        try {
            lifeImage = ImageIO.read(new File("resources/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void paintIcon(Component comp, Graphics g2, int x, int y) {
		g2.drawImage(lifeImage, x, y, width, height, null);
	}

}
