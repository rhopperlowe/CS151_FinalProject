import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameInstance extends JFrame {
	private Timer 						timer;
	private GameModel 					model;
	 
	private static final int REPAINT_DELAY = 5;


	public GameInstance() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		model = new GameModel();

		 
		this.addKeyListener(new KeyAdapter() {
			@Override
           public void keyPressed(KeyEvent e) {
        	   super.keyTyped(e);

        	   int keyCode = e.getKeyCode();

               if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
            		   || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
						|| keyCode == KeyEvent.VK_SPACE)
               {
            	   if (model.getState() == GameModel.PLAYING || model.getState() == GameModel.DRONE_IDLE)
            		   model.moveDrone(keyCode);
               }
           }
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
	            		   || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
							|| keyCode == KeyEvent.VK_SPACE)
				{
					if (model.getState() == GameModel.PLAYING)
						model.changeState(GameModel.DRONE_IDLE);
				}
			}
		});

		this.add(model);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 550);
		this.setVisible(true);
		
		
		timer = new Timer(REPAINT_DELAY, event -> {
		    model.repaint();
		});
		timer.start();
	}

	}

