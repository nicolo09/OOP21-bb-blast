package bbblast.utils;

/**
 * This interface defines a VectorConverter, an object that converts the module
 * and angle into vector components.
 */
public interface VectorConverter {

    /***
     * @param module the module of the vector.
     */
    void setModule(int module);

    /***
     * @param angle the angle between the vector and the axis. Angle is in degrees
     */
    void setAngle(int angle);

    /**
     * 
     * @return the position, the components of the vector
     */
    Position getComponents();

}
