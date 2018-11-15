import java.awt.*;
import java.awt.geom.*;

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

    boolean contains(Point2D p);

    void move();


}

