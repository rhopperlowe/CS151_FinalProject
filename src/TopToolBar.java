import javax.swing.*;
import java.awt.*;

public class TopToolBar extends JPanel {
	private JLabel							points;
	private Clock							clock;
	
	public TopToolBar() {
		this.setLayout(new BorderLayout());
		
		points = new JLabel("Points: 0");
		clock = new Clock();
		clock.start();
		
		this.add(points, BorderLayout.WEST);
		this.add(clock, BorderLayout.EAST);
	}
	
	
	public void setPoints(int num) {
		points.setText("Points: " + num);
	}
	
	public void pauseGameClock() {
		clock.stop();
	}
	
	public void resumeGameClock() {
		clock.start();
	}
}
