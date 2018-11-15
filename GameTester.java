import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 A program that allows users to edit a scene composed
 of items.
 */
public class GameTester
{
    private Timer timer;
    private DroneShape done;
    private final int DELAY = 10;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final SceneComponent scene = new SceneComponent();

        DroneShape drone = new DroneShape(20,20);

        for(int i=0; i < 6; i++){
            scene.add(new EnemyShape(300,i*50));
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);

                if(e.getKeyCode() == 38){
                    drone.setDy(-1);
                    drone.move();
                    scene.repaint();
                }

                if(e.getKeyCode() == 40){
                    drone.setDy(1);
                    drone.move();
                    scene.repaint();
                }
            }
        });




        scene.add(drone);


        frame.add(scene, BorderLayout.CENTER);
        frame.setSize(500, 500);
        frame.setVisible(true);

        final int DELAY = 50;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {
            scene.moveEnemys();
            scene.repaint();
        });
        t.start();


    }

    private void step() {
        
    }
}


