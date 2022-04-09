package bbblast.utils;
/**
 * 
 * The interface which models a Position
 *
 */
public interface Position {

	/**
	 * @return the x coordinate of this position
	 */
	double getX();
	
	/**
	 * @return the y coordinate of this position
	 */
	double getY();
	
	/**
	 * Translates this position so that it represents (x + dx, y + dy) location.
	 * @param dx distance on the x axis
	 * @param dy distance on the y axis
	 */
	void translate(double dx, double dy);
	
	/**
	 * Switches the current coordinates with new ones.
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	void setCoords(double x, double y);
	
	/**
	 * @return a copy of this Position
	 */
	Position getCopy();
}
