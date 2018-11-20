package src;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import java.util.*;

/**
 A component that shows a scene composed of shapes.
 */

public class SceneComponent extends JComponent
{
    private DroneShape 						drone;
    private ArrayList<EnemyShape>			enemies;

    private GameModel						model;

    public SceneComponent()
    {
    	this.drone = new DroneShape(20, 20);
        this.enemies = new ArrayList<>();

        model = new GameModel(this);
    }


    /**
     Adds a shape to the scene.
     @param s the shape to add
     */
    public void setDrone(DroneShape newDrone)
    {
        drone = newDrone;
    }
    
    public boolean addEnemy(EnemyShape enemy) {
    	return enemies.add(enemy);
    }

    public boolean removeEnemy(EnemyShape enemy) {
    	boolean success = enemies.remove(enemy);
    	repaint();
    	return success;
    }

    /**
     Removes all selected shapes from the scene.
     */
    public void removeSelected()
    {

    }

    public void moveEnemys(){
        for(int i = enemies.size() - 1; i >= 0; i--){
        	EnemyShape s = enemies.get(i);
            if(s instanceof EnemyShape){
                s.move();
                if(s.contains(new Point2D.Double(drone.getX() + 50,drone.getY()))) {
                    System.out.println("DRONE HIT");
                    enemies.remove(s);
                    model.crash();
                    repaint();
                }
            }
        }
    }

    public void moveDrone(int keyCode) {
    	if(keyCode == KeyEvent.VK_UP)
            drone.setDy(-1);
       else if(keyCode == KeyEvent.VK_DOWN)
            drone.setDy(1);
       else
    	   drone.setDy(0);

       if (keyCode == KeyEvent.VK_LEFT)
    	   drone.setDx(-1);
       else if (keyCode == KeyEvent.VK_RIGHT)
    	   drone.setDx(1);
       else
        	drone.setDx(0);

       drone.move();
       repaint();
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        drone.draw(g2);
        for (SceneShape s : enemies)
        {
            s.draw(g2);
        }
    }

//    private ArrayList<SceneShape> shapes;
}
