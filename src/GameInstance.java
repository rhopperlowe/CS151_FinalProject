import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GameInstance extends JFrame {
	private SceneComponent				scene;
	private int							state;
	
	public GameInstance() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		state = GameModel.PLAYING;
		scene = new SceneComponent();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
           public void keyPressed(KeyEvent e) {
			   super.keyTyped(e);
        	   
        	   int keyCode = e.getKeyCode();   
               if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
            		   || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            	   scene.moveDrone(keyCode);
               }
           }
		});
		
		this.add(scene, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	 
}
