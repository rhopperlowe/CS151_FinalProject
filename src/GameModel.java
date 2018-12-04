//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 
 * Model class for MVC
 *
 */
public class GameModel extends JPanel
{
	private SceneComponent scene;
	private BottomToolBar bottomTools;
	private TopToolBar topTools;

	private int state, wave, lives, points, spawned;

	//timer variables
	private Timer spawnTimer, droneIdleTimer, enemyMovementTimer, freezeTimer, waveTimer, pointTimer, cloudTimer, cloudMovementTimer;

	//state values
	public static final int START_MENU = 1;
	public static final int PLAYING = 2;
	public static final int GAME_OVER = 0;
	public static final int DRONE_FROZEN = 3;
	public static final int DRONE_IDLE = 4;

	//final int variables for delay
	private static final int DRONE_IDLE_DELAY = 20;
	private static final int MOVEMENT_DELAY = 10;
	private static final int CLOUD_MOVEMENT_DELAY = 20;
	private static final int ENEMY_SPAWN_DELAY = 1_000;
	private static final int NEW_WAVE_DELAY = 8_000;
	private static final int ADD_POINT_DELAY = 90_000;
	private static final int DRONE_FROZEN_DELAY = 5_000; //5 seconds delay when drone is hit
	private static final int CLOUD_SPAWN_DELAY = 3_000;

	public GameModel()
	{
		this.setLayout(new BorderLayout());
		
		this.scene = new SceneComponent(this);
		this.add(scene, BorderLayout.CENTER);

//		scene.displayStartMenu();
		wave = 0;
		spawned = 0;
		points = 0;
		lives = 3;

		bottomTools = new BottomToolBar();
		topTools = new TopToolBar();
		
		scene.add(topTools, BorderLayout.NORTH);
		scene.add(bottomTools, BorderLayout.SOUTH);

		droneIdleTimer = new Timer(DRONE_IDLE_DELAY, event ->
		{
			scene.droneIdle();
		});
		
		enemyMovementTimer = new Timer(MOVEMENT_DELAY, event ->
		{
		    scene.moveEnemies();
		});

		cloudMovementTimer = new Timer(CLOUD_MOVEMENT_DELAY, event ->
		{
		    scene.moveClouds();
        });

		spawnTimer = new Timer(ENEMY_SPAWN_DELAY, event ->
		{
        	if (spawned < wave && spawned < 7)
        	{
        		Random rand = new Random();
        		scene.addEnemy(new EnemyShape(500,rand.nextInt(400))); //creates overlapping
        		spawned++;
        	}
        	else
			{
				spawnTimer.stop();
			}
        });

		spawnTimer.setInitialDelay(0);
		
		waveTimer = new Timer(NEW_WAVE_DELAY, event ->
		{
			scene.removeAllEnemies();
			bottomTools.setWave(++wave);
			
			spawned = 0;
			spawnTimer.restart();
			
			waveTimer.setDelay( (wave * 1_000) + NEW_WAVE_DELAY );
		});

		waveTimer.setInitialDelay(1000);
		
		freezeTimer = new Timer(DRONE_FROZEN_DELAY, event ->
		{
			if (state == DRONE_FROZEN) {
				this.changeState(DRONE_IDLE);
			}
		});
		
		pointTimer = new Timer(ADD_POINT_DELAY, event ->
		{
			topTools.setPoints(++points);
		});

		cloudTimer = new Timer(CLOUD_SPAWN_DELAY, event ->
		{
			Random rand = new Random();
			scene.addCloud(new CloudShape(500,rand.nextInt(400)));
		});

		waveTimer.start();
		droneIdleTimer.start();
		enemyMovementTimer.start();
		cloudMovementTimer.start();
		pointTimer.start();
		cloudTimer.start();
		
		state = PLAYING;
	}
	
	
	public int getState()
	{
		return state;
	}
	
	public void changeState(int newState)
	{
		if (newState == GAME_OVER)
		{
			state = GAME_OVER;
		}
		else if (newState == PLAYING)
		{
			state = PLAYING;
		}
		else if (newState == DRONE_IDLE)
		{
			state = DRONE_IDLE;
			droneIdleTimer.restart();
		}
	}
	
	public void moveDrone(int keyCode)
    {
        scene.moveDrone(keyCode);
    }
	
	public void startDroneIdle() {
		if (state == PLAYING)
        {
           state = DRONE_IDLE;
           droneIdleTimer.restart();
        }
	}

	public void stopDroneIdle()
	{
		state = PLAYING;
		droneIdleTimer.stop();
	}

	public void gameOver()
	{
		System.out.println("Game Over!");
		this.stopAllTimers();
		topTools.pauseGameClock();
		state = GAME_OVER;
		scene.displayGameOver();
	}
	
	public void stopAllTimers()
	{
		spawnTimer.stop();
		enemyMovementTimer.stop();
		freezeTimer.stop();
		waveTimer.stop();
		pointTimer.stop();
		droneIdleTimer.stop();
		cloudTimer.stop();
		cloudMovementTimer.stop();
	}

	public void crash()
	{
		bottomTools.loseLife();
		lives--;
		
		if (state == DRONE_IDLE)
		{
			this.stopDroneIdle();
		}
		
		if (lives == 0)
		{
			this.gameOver();
		}
		else if (state == PLAYING)
		{
			state = DRONE_FROZEN;
			freezeTimer.restart();
		}
	}

}
