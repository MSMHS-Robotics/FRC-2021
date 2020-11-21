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

    /** Resets the encoder's ticks to 0 */
    public void reset() {
        this.ticks = 0;
    }

    /** Gets the current tick count
     * @return the current tick count
     */
    public double getPosition() {
        return ticks;
    }
}