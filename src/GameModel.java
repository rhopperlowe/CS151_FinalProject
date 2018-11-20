import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;

public class GameModel {	
	private SceneComponent				scene;
	private ToolBar						tools;
	private int							state;
	
	private Timer						enemyTimer;
	private Timer						waveTimer;
	private Timer						gameTimer;
	
	private int							wave;
	private int							lives;
		
	private static final int ENEMY_MOVEMENT_DELAY = 5;
	private static final int NEW_WAVE_DELAY = 5000;
	
	public static final int SETTING_UP = 0;
	public static final int PLAYING = 1;
	public static final int GAME_OVER = 2;
	
	public GameModel(SceneComponent scene) {
		this.scene = scene;
		state = PLAYING;
		wave = 0;
		lives = 3;
		
		tools = new ToolBar();
		scene.add(tools, BorderLayout.SOUTH);
		scene.repaint();
				
		enemyTimer = new Timer(ENEMY_MOVEMENT_DELAY, event ->
		{
		    scene.moveEnemys();
		    scene.repaint();
		});
		
		waveTimer = new Timer(NEW_WAVE_DELAY, event -> {
			wave++;
			for (int i = 0; i < 2; i++) {
				scene.addEnemy(new EnemyShape(300, i * 50));
			}
		});
		
		waveTimer.start();
		enemyTimer.start();
		
	}
	
	public int getWave() {
		return wave;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getState() {
		return state;
	}
	
	public void changeState(int newState) {
		if (state < 3 && state > 0 && state != newState)
			state = newState;
	}
	
	
	public void gameOver() {
		System.out.println("Game Over!");
		enemyTimer.stop();
		waveTimer.stop();
		tools.pauseGameClock();
		state = GAME_OVER;
		scene.changeState(GAME_OVER);
	}
	
	public void crash() {
		tools.loseLife();
		lives--;
		
		if (lives == 0)
			this.gameOver();
		
	}
	
}
