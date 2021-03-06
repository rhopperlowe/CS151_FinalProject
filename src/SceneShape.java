//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import java.awt.*;

/**
    A shape that is a part of a scene.
 */

public interface SceneShape
{
     /**
         Draws this item.
         @param g2 the graphics context
     */

     void draw(Graphics2D g2);

     /**
         Tests whether this item contains a given point.
         @param p a point
         @return true if this item contains p
     */

     boolean contains(Polygon p);

     /**
         Moves this item.
     */

     void move();

}

