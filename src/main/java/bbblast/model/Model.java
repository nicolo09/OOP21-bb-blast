package bbblast.model;

import java.util.Collection;

public interface Model {
	
	void update();
	
	Collection<Bubble> getBubbles();
	
	void moveCannon();
	
	void shootCannon();
	
	int getCannonAngle();
	
	int getScore();
	
	boolean isGameOver();
	
	void switchBubble();
	
	Settings loadSettings();
	
	void writeSettings(Settings s);
	
}
