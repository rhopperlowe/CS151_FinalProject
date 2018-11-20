package src;



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
		    scene.moveEnemys();
		    scene.repaint();
		});

		waveTimer = new Timer(NEW_WAVE_DELAY, event -> {
            Random rand = new Random();
            for (int i =0; i <= wave; i++){
                scene.addEnemy(new EnemyShape(500,rand.nextInt(450))); //creates overlapping

            }
            wave++;
		});

		waveTimer.start();

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

	}

}
