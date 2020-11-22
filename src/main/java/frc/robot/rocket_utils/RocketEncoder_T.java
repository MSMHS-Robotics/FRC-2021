package frc.robot.rocket_utils;

/** A class to simulate an Encoder */
public class RocketEncoder_T {
    private int port1, port2;
    private double ticks = 0;
    private double revsPerTick = 1;
    
    /** 
     * Creates a new RocketEncoder
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

    /**
     * Sets the distance traveled per pulse (aka revolutions per tick)
     * this will most likely be a tiny jumbled decimal
     * @param revsPerTick the number of revolutions per tick
     */
    public void setDistancePerPulse(double revsPerTick) {
        this.revsPerTick = revsPerTick;
    }

    /**
     * returns the distance (current tick count * revolutions per tick)
     * @return a double representing the number of revolutions the encoder has completed
     */
    public double getDistance() {
        return ticks * revsPerTick;
    }

    /** 
     * Gets the current tick count
     * @return the encoder's current tick count
     */
    public double get() {
        return ticks;
    }
}