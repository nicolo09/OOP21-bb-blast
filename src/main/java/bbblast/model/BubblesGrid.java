package bbblast.model;

import java.util.Collection;

public interface BubblesGrid {
	
	Collection<Bubble> getBubbles();
	
	int getLastRowY();
	
	void addBubble(Position p);
	
	void removeBubble(Position p);

}
