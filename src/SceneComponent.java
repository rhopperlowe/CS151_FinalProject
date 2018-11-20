package src;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/**
 A component that shows a scene composed of shapes.
 */

public class SceneComponent extends JComponent
{
    private DroneShape drone;
    private ArrayList<EnemyShape>			enemies;

    public SceneComponent(DroneShape drone)
    {
    	this.drone = drone;
        this.enemies = new ArrayList<>();
    }


    /**
     Adds a shape to the scene.
     @param newDrone the shape to add
     */
    public void setDrone(DroneShape newDrone)
    {
        drone = newDrone;
    }
    
    public boolean addEnemy(EnemyShape enemy) {
    	return enemies.add(enemy);
    }

    /**
     Removes all selected shapes from the scene.
     */
    public void removeEnemy(SceneShape s)
    {
        enemies.remove(s);
    }

    public void moveEnemys(){
        for(SceneShape s : enemies){
            if(s instanceof EnemyShape){
                s.move();
                if(s.contains(drone.getHitbox()))
                    System.out.println("DRONE HIT");
            }
        }
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        drone.draw(g2);
        for (SceneShape s : enemies)
        {
            s.draw(g2);
            if(s.getX() >= 550)
                removeEnemy(s);
        }
    }

//    private ArrayList<SceneShape> shapes;
}
