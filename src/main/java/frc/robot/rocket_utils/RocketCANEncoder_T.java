package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

/** A class to simulate a CANEncoder */
public class RocketCANEncoder_T {
    private RocketSparkMAX_T motor;
    private double ticks = 0;
    private double lastTime = 0;
    private double lastTicks = 0;
    private double scaleFactor = 1;
    private Timer timer;

    /**
     * Creates a new RocketCANEncoder
     * @param motor a RocketSparkMAX_T (because this class is a _T and will only ever get _Ts)
     */
    public RocketCANEncoder_T(RocketSparkMAX_T motor) {
        this.motor = motor;
        timer = new Timer();
    }

    /**
     * Sets the internal tick count to the given tick value
     * setting it to 0 is pretty much equivalent to resetting it (which the CANEncoders don't have as a method)
     * @param ticks the number you want the tick count to be
     */
    public void setPosition(double ticks) {
        this.ticks = ticks;
        this.lastTicks = ticks;
    }

    /**
     * gets the velocity in RPM
     * divides tick count minus tick count last time this was called by time minus
     * the time when this was called last. Then devides _that_ by ticks per rev of the NEO encoder
     * @return the current velocity in RPMs
     */
    public double getVelocity() {
        double ticksSinceLast = (ticks - lastTicks) / (timer.getFPGATimestamp() - lastTime);
        lastTicks = ticks;
        lastTime = timer.getFPGATimestamp();
        return ticksSinceLast / Constants.neoTicksPerRev;
    }

    /**
     * Sets the scale factor used by getPosition() to return ticks
     * @param factor the value you want the scale factor set to
     */
    public void setPositionConversionFactor(double factor) {
        scaleFactor = factor;
    }

    /** 
     * Gets the current tick count
     * @return the current tick count
     */
    public double getPosition() {
        return ticks * scaleFactor;
    }
}