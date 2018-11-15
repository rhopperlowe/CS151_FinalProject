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
            }
        }
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;
        for (SceneShape s : shapes)
        {
            s.draw(g2);
        }
    }

    private ArrayList<SceneShape> shapes;
}
