import javax.swing.*;
import java.awt.*;

public class BottomToolBar extends JPanel {
	private JComponent						lives;
	private JLabel							wave;
	
	public BottomToolBar(){
		lives = new JPanel();		
		for (int i = 0; i < 3; i++) {
			JLabel label = new JLabel(new LifeShape(20));
			lives.add(label);
		}
		
		wave = new JLabel("Wave: 0");
				
		this.setLayout(new BorderLayout());
		this.add(lives, BorderLayout.WEST);
		this.add(wave, BorderLayout.EAST);
	}
	
	public void loseLife() {
		int length = lives.getComponentCount() - 1;
		lives.remove(lives.getComponents()[length]);
	}
	
	public void setWave(int wave) {
		this.wave.setText("Wave: " + wave);
	}
}
