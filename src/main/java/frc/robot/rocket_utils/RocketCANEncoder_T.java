package frc.robot.rocket_utils;

/** A class to simulate a CANEncoder */
public class RocketCANEncoder_T {
    private RocketSparkMAX_T motor;
    private double ticks = 0;

    /**
     * Creates a new RocketCANEncoder
     * @param motor a RocketSparkMAX_T (because this class is a _T and will only ever get _Ts)
     */
    public RocketCANEncoder_T(RocketSparkMAX_T motor) {
        this.motor = motor;
    }

    /**
     * Sets the internal tick count to the given tick value
     * setting it to 0 is pretty much equivalent to resetting it (which the CANEncoders don't have as a method)
     * @param ticks the number you want the tick count to be
     */
    public void setPosition(double ticks) {
        this.ticks = ticks
    }

    /** 
     * Gets the current tick count
     * @return the current tick count
     */
    public double getPosition() {
        return ticks;
    }
}