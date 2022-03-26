package bbblast.model;

/**
 * 
 * The Interface which models a Cannon.
 *
 */
public interface Cannon {
	
	/**
	 * moves the Cannon by the specified angle.
	 * @param angle
	 */
	void move(int angle);
	
	/**
	 * makes the Cannon shoot a Bubble. 
	 */
	void shoot();
	
	/**
	 * switches the Bubble inside the Cannon with the outside one.
	 */
	void exchange();
	
	/**
	 * @return the current angle of the Cannon
	 */
	int getAngle();

}
