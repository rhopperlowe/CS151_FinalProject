import javax.swing.*;
import java.util.Random;

public class GameModel {
	private SceneComponent scene;

	private Timer						enemyTimer;
	private Timer						waveTimer;

	private int							wave;

	private int							lives;


	private static final int MOVEMENT_DELAY = 10;
	private static final int NEW_WAVE_DELAY = 5000;

	public GameModel(SceneComponent scene) {
		this.scene = scene;

		wave = 1;
		lives = 3;


		enemyTimer = new Timer(MOVEMENT_DELAY, event ->
		{
		    scene.moveEnemies();
		    scene.repaint();
		});

		waveTimer = new Timer(NEW_WAVE_DELAY, event -> {
            spawnEnemies(wave);
            wave++;
		});

		waveTimer.start();

		//sends enemy at start of game
		spawnEnemies(1);
		wave++;

		enemyTimer.start();


	}


	public void gameOver() {
		System.out.println("Game Over!");
		enemyTimer.stop();
		waveTimer.stop();
	}

	public void crash() {
		lives--;
		if (lives == 0)
			this.gameOver();
		System.out.println("Number of Lives " + lives);

	}

	public void spawnEnemies(int amount){
        Random rand = new Random();
        for (int i =0; i < amount; i++){
            scene.addEnemy(new EnemyShape(500,rand.nextInt(400))); //creates overlapping
        }
    }




}
