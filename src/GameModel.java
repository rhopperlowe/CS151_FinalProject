import java.util.ArrayList;

import javax.swing.*;

public class GameModel {
	private SceneComponent				scene;
	private Timer						timer;
	
	private DroneShape					drone;
	private ArrayList<EnemyShape>		enemies;
	
	private static final int DELAY = 10;
	
	public GameModel(SceneComponent scene) {
		this.scene = scene;
		
		
		drone = new DroneShape(20, 20);
		enemies = new ArrayList<>();
		for(int i=0; i < 2; i++){
	        enemies.add(new EnemyShape(300,i*50));
	    }
		
		
		timer = new Timer(DELAY, event ->
		{
		    scene.moveEnemys();
		
		    scene.repaint();
		});
		
		timer.start();
	}
}
