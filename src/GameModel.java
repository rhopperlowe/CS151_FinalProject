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

	/**
	 * constructs model for the game
	 */
	public GameModel()
	{
		this.setLayout(new BorderLayout());
		
		//constructs view with reference to model
		this.scene = new SceneComponent(this);
		this.add(scene, BorderLayout.CENTER);

//		scene.displayStartMenu();
		
		//start with new data, and full lives
		wave = 0;
		spawned = 0;
		points = 0;
		lives = 3;

		bottomTools = new BottomToolBar();
		topTools = new TopToolBar();
		
		scene.add(topTools, BorderLayout.NORTH);
		scene.add(bottomTools, BorderLayout.SOUTH);
		
		//
		//initialize all game timers
		//
		
		//moves drone forward when no buttons are pressed
		droneIdleTimer = new Timer(DRONE_IDLE_DELAY, event ->
		{
			scene.droneIdle();
		});
		
		//moves enemies forward at consistent speed
		enemyMovementTimer = new Timer(MOVEMENT_DELAY, event ->
		{
		    scene.moveEnemies();
		});
		
		//moves clouds forward
		cloudMovementTimer = new Timer(CLOUD_MOVEMENT_DELAY, event ->
		{
		    scene.moveClouds();
        });

		//spawns enemies once every second until the wave number is reached
		//no more than 6 enemies will be spawned each wave
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
		
		//sets the time until the next wave begins
		waveTimer = new Timer(NEW_WAVE_DELAY, event ->
		{
			scene.removeAllEnemies();
			bottomTools.setWave(++wave);
			
			spawned = 0;
			spawnTimer.restart();
			
			waveTimer.setDelay( (wave * 1_000) + NEW_WAVE_DELAY );
		});
		waveTimer.setInitialDelay(1000);
		
		//determines how long the drone is frozen for after collision
		freezeTimer = new Timer(DRONE_FROZEN_DELAY, event ->
		{
			if (state == DRONE_FROZEN) {
				this.changeState(DRONE_IDLE);
			}
		});
		
		//adds a point every 90 seconds
		pointTimer = new Timer(ADD_POINT_DELAY, event ->
		{
			topTools.setPoints(++points);
		});

		//spawns a new cloud every so often
		cloudTimer = new Timer(CLOUD_SPAWN_DELAY, event ->
		{
			Random rand = new Random();
			scene.addCloud(new CloudShape(500,rand.nextInt(400)));
		});

		//start timers
		waveTimer.start();
		droneIdleTimer.start();
		enemyMovementTimer.start();
		cloudMovementTimer.start();
		pointTimer.start();
		cloudTimer.start();
		
		//set state to begin game
		state = PLAYING;
	}
	
	
	
	/**
	 * @return state
	 */
	public int getState()
	{
		return state;
	}
	
	/**
	 * @param newState state to change model to
	 */
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
	
	/**
	 * @param keyCode code to send to the view for shape movement
	 */
	public void moveDrone(int keyCode)
    {
        scene.moveDrone(keyCode);
    }
	
	/**
	 * sets the drone to the idle state
	 * and begins pushing it forward in the view
	 */
	public void startDroneIdle() {
		if (state == PLAYING)
        {
           state = DRONE_IDLE;
           droneIdleTimer.restart();
        }
	}

	/**
	 * takes the drone out of the idle state
	 * - used for when user presses an arrowkey
	 */
	public void stopDroneIdle()
	{
		state = PLAYING;
		droneIdleTimer.stop();
	}

	/**
	 * ends the game
	 */
	public void gameOver()
	{
		System.out.println("Game Over!");
		this.stopAllTimers();
		topTools.pauseGameClock();
		state = GAME_OVER;
		scene.displayGameOver();
	}
	
	/**
	 * stops all timers at once to end the game
	 */
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

	/**
	 * collision handler:
	 * when the drone collides with an enemy the user
	 * loses a life
	 * if the user has no remaining lives
	 * then gameOver() is called.
	 * 
	 * if the user still has lives left
	 * then the drone is frozen for 5 seconds and cannot move
	 * or shoot lasers
	 */
	public void crash()
	{
		//lose life
		bottomTools.loseLife();
		lives--;
		
		//stop idle
		if (state == DRONE_IDLE)
		{
			this.stopDroneIdle();
		}
		
		//check for 0 lives and end game if necessary
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
