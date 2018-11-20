import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
	private JComponent						lives;
	private Clock							clock;
	
	public ToolBar(){
		lives = new JPanel();		
		for (int i = 0; i < 3; i++) {
			JLabel label = new JLabel(new LifeShape(20));
			lives.add(label);
		}
		
		clock = new Clock();
		clock.start();
				
		this.setLayout(new BorderLayout());
		this.add(lives, BorderLayout.WEST);
		this.add(clock, BorderLayout.EAST);
	}
	
	public void loseLife() {
		lives.remove(lives.getComponents()[0]);
	}
	
	public void pauseGameClock() {
		clock.stop();
	}
	
	public void resumeGameClock() {
		clock.start();
	}
	
}
