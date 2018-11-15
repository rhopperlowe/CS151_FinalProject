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
    private DroneShape drone;

    public SceneComponent()
    {
        shapes = new ArrayList<SceneShape>();

    }


    /**
     Adds a shape to the scene.
     @param s the shape to add
     */
    public void add(SceneShape s)
    {
        if(s instanceof DroneShape){
            drone = (DroneShape) s;
        }

        else
            shapes.add(s);

    }

    /**
     Removes all selected shapes from the scene.
     */
    public void removeSelected()
    {

    }

    public void moveEnemys(){
        for(SceneShape s : shapes){
            if(s instanceof EnemyShape){
                s.move();
                if(s.contains(new Point2D.Double(drone.getX(),drone.getY())))
                    System.out.println("DRONE HIT");

            }

        }
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        drone.draw(g2);
        for (SceneShape s : shapes)
        {
            s.draw(g2);
        }
    }

    private ArrayList<SceneShape> shapes;
}
