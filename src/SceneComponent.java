//CS151 Group Project
//Ryan
//Sebastian
//Ezana

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 A component that shows a scene composed of shapes.
 */

public class SceneComponent extends JComponent
{
    private DroneShape drone;
    private ArrayList<EnemyShape> enemies;
    private ArrayList<CloudShape> cloudShapes;
    private BufferedImage mountainimage;
    private LaserShape laser;
    private GameModel model;

    private int laserStart;

    public SceneComponent(GameModel model)
    {
    	this.setLayout(new BorderLayout());
    	this.drone = new DroneShape(20, 20);
        this.enemies = new ArrayList<>();
        this.cloudShapes= new ArrayList<>();

        this.model = model;

        try
        {
            mountainimage = ImageIO.read(new File("resources/mountains.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean addEnemy(EnemyShape enemy)
    {
    	return enemies.add(enemy);
    }

    public boolean removeEnemy(EnemyShape enemy)
    {
    	boolean success = enemies.remove(enemy);
    	return success;
    }

    public void removeAllEnemies()
    {
    	enemies.clear();
    }
    
    public void droneIdle()
    {
    	if (model.getState() != GameModel.DRONE_IDLE)
    		return;
    		
    	drone.setDx(1);
    	drone.setDy(0);
    	drone.move();
    }


    public void moveEnemies()
    {
    	if (model.getState() == GameModel.GAME_OVER)
        {
            return;
        }

        for(int i = enemies.size() - 1; i >= 0; i--)
        {
        	EnemyShape s = enemies.get(i);

            if(s instanceof EnemyShape)
            {
                s.move();
                if(s.contains(drone.getHitbox()))
                {
                    System.out.println("DRONE HIT");
                    removeEnemy(s);
                    model.crash();
                }

                if(laser!= null && s.contains(laser.getHitbox()))
                {
                    removeEnemy(s);
                    laser = null;
                }
            }
        }
    }

    public void moveClouds()
    {
        for (int i = cloudShapes.size() - 1; i >= 0; i--)
        {
            CloudShape s = cloudShapes.get(i);
            s.move();
        }
    }

    public void addCloud(CloudShape c)
    {
        cloudShapes.add(c);
    }

    public void moveDrone(int keyCode)
    {
        if (model.getState() != GameModel.PLAYING && model.getState() != GameModel.DRONE_IDLE)
        {
            return;
        }

        if(keyCode == KeyEvent.VK_SPACE)
        {
            laser = new LaserShape(drone.getX() + 50, drone.getY() + 25);
            laserStart = laser.getX();
        }
        
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || 
        	keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT )
        {
            model.stopDroneIdle();
        }
        	

       if (keyCode == KeyEvent.VK_UP && drone.getY() > 20)
       {
           drone.setDy(-4);
       }
       else if(keyCode == KeyEvent.VK_DOWN && drone.getY() < 400)
       {
           drone.setDy(4);
       }
       else
       {
           drone.setDy(0);
       }

       if (keyCode == KeyEvent.VK_LEFT && drone.getX() > 0)
       {
           drone.setDx(-4);
       }
       else if (keyCode == KeyEvent.VK_RIGHT && drone.getX() < 420)
       {
           drone.setDx(4);
       }
       else
       {
           drone.setDx(0);
       }

       drone.move();
    }

    public void displayStartMenu() {
        JLabel startGameLabel = new JLabel();
        startGameLabel.setText("<html><div style='text-align: center;'>" + "Start Game" + "</div></html>");
        startGameLabel.setBounds(136, 10, 750, 375);
        startGameLabel.setFont(new Font("Serif", Font.BOLD, 35));
        startGameLabel.setForeground(Color.white);

        add(startGameLabel);
        setVisible(true);

        JButton startButton=new JButton("Click Here To Start");
        startButton.setBounds(127,230,190,30);
        add(startButton);
        setSize(400,400);
        setLayout(null);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void displayGameOver ()
    {
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setText("Game Over!");
        gameOverLabel.setBounds(0, 40, 1500, 375);
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 90));
        gameOverLabel.setForeground(Color.white);

        add(gameOverLabel);
        setVisible(true);
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(12, 122, 255));
        g2.fillRect(0, 0, 500, 500); //set background fix later
        g2.setColor(Color.green);
        g2.drawImage(mountainimage,0, 385, 500,100, null);

        for (CloudShape c : cloudShapes)
        {
            c.draw(g2);
        }

        drone.draw(g2);

        if(laser != null && laser.getX() < ( laserStart + 105 ) )
        {
            laser.move();
            laser.draw(g2);
        }
        else
        {
            laser = new LaserShape(); //create new laserShape to remove previous laser after getting to its limit (1/4th of the screen)
        }

        for (SceneShape s : enemies)
        {
            s.draw(g2);
        }
    }
}
