import javax.swing.*;

public class Clock extends JLabel {
	private int				seconds;
	private int				minutes;
	private int				hours;
	
	private Timer			timer;
	
	public Clock() {
		seconds = 0;
		minutes = 0;
		hours = 0;
		
		timer = new Timer(1000, event -> {
			this.increment();
			this.setText(this.toString());
		});
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void increment() {
		if (++seconds >= 60) {
			minutes++;
			seconds = 0;
		}
	}
	
	public int getSeconds() {
		int secs = seconds;
		for (int i = 0; i < minutes; i++)
			seconds += 60;
		
		return secs;
	}
	
	public String toString() {
		if (hours == 0)
			return String.format("%02d:%02d", minutes, seconds);
		return String.format("%2d:%2d:%2d", hours, minutes, seconds);
	}
}
