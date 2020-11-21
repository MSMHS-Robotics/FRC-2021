package frc.robot.rocket_utils;

/** A class to simulate an Encoder */
public class RocketEncoder_T {
    private int port1, port2;
    private double ticks = 0;
    
    /** Creates a new RocketEncoder
     * Has two ports because we are simulating WPI's Encoder (of the quadrature variety)
     * @param port1 the port of the (imaginary) 1st wire
     * @param port2 the port of the 2nd wire
     */
    public RocketEncoder_T(int port1, int port2) {
        this.port1 = port1;
        this.port2 = port2;
    }

    /** Resets the encoder's tick count to 0 */
    public void reset() {
        this.ticks = 0;
    }

    /** Gets the current tick count
     * @return the encoder's current tick count
     */
    public void get() {
        return ticks;
    }
}