package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

/** A class to simulate an Encoder */
public class RocketEncoder_T extends Encoder {
    private int port1, port2;
    private int ticks = 0;
    private double revsPerTick = 1;
    private double lastTime = 0;
    private double lastTicks = 0;
    
    /** 
     * Creates a new RocketEncoder
     * Has two ports because we are simulating WPI's Encoder (of the quadrature variety)
     * @param port1 the port of the (imaginary) 1st wire
     * @param port2 the port of the 2nd wire
     */
    public RocketEncoder_T(int port1, int port2) {
        super(port1, port2);
        this.port1 = port1;
        this.port2 = port2;
    }

    /** Resets the encoder's tick count to 0 */
    public void reset() {
        this.ticks = 0;
        this.lastTicks = ticks;
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
    public int get() {
        return ticks;
    }

    /**
     * gets the velocity in RPM
     * divides tick count minus tick count last time this was called by time minus
     * the time when this was called last. Then devides _that_ by ticks per rev of the through-bore encoder
     * @return the current velocity in RPMs
     */
    @Override
    public double getRate() {
        double ticksSinceLast = (ticks - lastTicks) / (Timer.getFPGATimestamp() - lastTime);
        lastTicks = ticks;
        lastTime = Timer.getFPGATimestamp();
        return ticksSinceLast / Constants.encoderTicksPerRev;
    }
}