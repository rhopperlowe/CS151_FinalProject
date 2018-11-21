import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 A component that shows a scene composed of shapes.
 */

public class SceneComponent extends JComponent
{
    private DroneShape 						drone;
    private ArrayList<EnemyShape>			enemies;
    private BufferedImage                   mountainimage;

    private GameModel						model;

    public SceneComponent()
    {
    	this.setLayout(new BorderLayout());
    	
    	this.drone = new DroneShape(20, 20);
        this.enemies = new ArrayList<>();

        model = new GameModel(this);

        try {
            mountainimage = ImageIO.read(new File("resources/mountains.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    
    public boolean addEnemy(EnemyShape enemy) {
    	return enemies.add(enemy);
    }

    public boolean removeEnemy(EnemyShape enemy) {
    	boolean success = enemies.remove(enemy);
    	repaint();
    	return success;
    }
    
    public void removeAllEnemies() {
    	enemies.clear();
    	this.repaint();
    }


    public void moveEnemies() {
    	if (model.getState() == GameModel.GAME_OVER)
    		return;
    	
        for(int i = enemies.size() - 1; i >= 0; i--){
        	EnemyShape s = enemies.get(i);
            if(s instanceof EnemyShape){
                s.move();
                if(s.contains(drone.getHitbox())) {
                    System.out.println("DRONE HIT");
                    removeEnemy(s);
                    model.crash();
                    repaint();
                }
            }
        }
    }

    public void moveDrone(int keyCode) {
       if (model.getState() != GameModel.PLAYING)
    		return;
    	    	
       if (keyCode == KeyEvent.VK_UP && drone.getY() > 20)
            drone.setDy(-1);
       else if(keyCode == KeyEvent.VK_DOWN && drone.getY() < 400)
            drone.setDy(1);
       else
    	   drone.setDy(0);

       if (keyCode == KeyEvent.VK_LEFT && drone.getX() > 0)
    	   drone.setDx(-1);
       else if (keyCode == KeyEvent.VK_RIGHT && drone.getX() < 420)
    	   drone.setDx(1);
       else
        	drone.setDx(0);

       drone.move();
       repaint();
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(12, 122, 255));
        g2.fillRect(0,0,500,500);//set background fix later
        g2.setColor(Color.green);
        g2.drawImage(mountainimage,0,375,500,100,null);

        drone.draw(g2);
        for (SceneShape s : enemies) {
            s.draw(g2);
        }
    }
}
