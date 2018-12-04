//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameInstance extends JFrame
{
	private Timer 						timer;
	private GameModel					model;
	
	private static final int DELAY = 10;

	public GameInstance()
	{
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		model = new GameModel();
		 
		this.addKeyListener(new KeyAdapter()
		{
			@Override
            public void keyPressed(KeyEvent e)
			{
        	   super.keyTyped(e);

        	   int keyCode = e.getKeyCode();

               if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
            		   || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
						|| keyCode == KeyEvent.VK_SPACE)
               {
            	   model.moveDrone(keyCode);
               }
           }
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				int keyCode = e.getKeyCode();

				if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
	            		   || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT
							|| keyCode == KeyEvent.VK_SPACE)
				{
					model.startDroneIdle();
				}
			}
		});
		
		this.setSize(500, 550);
		this.add(model);
		this.setVisible(true);
		//scene.displayStartMenu();

		timer = new Timer(DELAY, event ->
		{
		    model.repaint();
		});

		timer.start();
	}
}

