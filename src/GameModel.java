import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameModel {
	private SceneComponent 				scene;
	private BottomToolBar				bottomTools;
	private TopToolBar					topTools;
	private int							state;

	private Timer						spawnTimer, enemyMovementTimer, waveTimer, pointTimer;

	private int							wave, lives, points, spawned;
	
	public static final int PLAYING = 1;
	public static final int GAME_OVER = 0;

	private static final int MOVEMENT_DELAY = 10;
	private static final int ENEMY_SPAWN_DELAY = 1_000;
	private static final int NEW_WAVE_DELAY = 8_000;
	private static final int ADD_POINT_DELAY = 90_000;

	public GameModel(SceneComponent scene) {
		this.scene = scene;

		wave = 0;
		spawned = 0;
		points = 0;
		lives = 3;

		bottomTools = new BottomToolBar();
		topTools = new TopToolBar();
		
		scene.add(topTools, BorderLayout.NORTH);
		scene.add(bottomTools, BorderLayout.SOUTH);
		scene.repaint();
		
		
		enemyMovementTimer = new Timer(MOVEMENT_DELAY, event -> {
		    scene.moveEnemies();
		    scene.repaint();
		});

		spawnTimer = new Timer(ENEMY_SPAWN_DELAY, event -> {
        	if (spawned < wave && spawned < 7) {
        		Random rand = new Random();
        		scene.addEnemy(new EnemyShape(500,rand.nextInt(400))); //creates overlapping
        		spawned++;
        	}
        	else
        		spawnTimer.stop();
        });
		spawnTimer.setInitialDelay(0);
		
		waveTimer = new Timer(NEW_WAVE_DELAY, event -> {
			bottomTools.setWave(++wave);
			spawned = 0;
			spawnTimer.restart();
//            spawnEnemies(wave);
		});
		waveTimer.setInitialDelay(1000);
		
		pointTimer = new Timer(ADD_POINT_DELAY, event -> {
			topTools.setPoints(++points);
		});

		waveTimer.start();
		enemyMovementTimer.start();
		pointTimer.start();
		
		state = PLAYING;
	}
	
	
	public int getState() {
		return state;
	}
	
	public void changeState(int newState) {
		if (newState == 0)
			state = GAME_OVER;
		else if (newState == 1)
			state = PLAYING;
	}


	public void gameOver() {
		System.out.println("Game Over!");
		enemyMovementTimer.stop();
		waveTimer.stop();
		topTools.pauseGameClock();
		state = GAME_OVER;
	}

	public void crash() {
		bottomTools.loseLife();
		lives--;
		if (lives == 0)
			this.gameOver();
	}

	public void spawnEnemies(int amount){
        Random rand = new Random();
        for (int i =0; i < amount; i++){
            scene.addEnemy(new EnemyShape(500,rand.nextInt(400))); //creates overlapping
        }
    }




}
