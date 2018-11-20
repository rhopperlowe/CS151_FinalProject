package src;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

public class GameInstance extends JFrame {
	private Timer 						timer;
	private SceneComponent				scene;
	 
	private static final int DELAY = 10;




	public GameInstance() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		DroneShape drone = new DroneShape(20, 20);
		scene = new SceneComponent(drone);
	 

		 
		 
		this.addKeyListener(new KeyAdapter() {
			@Override
           public void keyPressed(KeyEvent e) {
        	   super.keyTyped(e);
                
               if(e.getKeyCode() == KeyEvent.VK_UP)
                    drone.setDy(-1);
               else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    drone.setDy(1);
               else
            	   drone.setDy(0);
               
               if (e.getKeyCode() == KeyEvent.VK_LEFT)
            	   drone.setDx(-1);
               else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            	   drone.setDx(1);
               else
                	drone.setDx(0);
               
               drone.move();
               scene.repaint();
           }
		});
		
		this.add(scene, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.setVisible(true);

		spawnEnemys(5);
		
		timer = new Timer(DELAY, event ->
		{
		    scene.moveEnemys();
		    scene.repaint();

		});
		timer.start();
	}


	public void spawnEnemys(int numberofenemy){
		Random rand = new Random();
		for (int i =0; i <= numberofenemy; i++){
			scene.addEnemy(new EnemyShape(500,rand.nextInt(450))); //creates overlapping
		}
	}
	 
}
