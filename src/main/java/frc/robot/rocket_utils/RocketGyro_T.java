package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.SPI;

/** A simulated gyro (specifically a navX) */
public class RocketGyro_T extends RocketGyro {
    private double yaw = 0;
    private double yVelocity = 0;

    /** Creates a new RocketGyro */
    public RocketGyro_T(SPI.Port port) {
        super(port);
    }    

    /**
     * resets the gyro
     */
    @Override
    public void reset() {
        yaw = 0;
        yVelocity = 0;
    }
    
    /**
     * Gets the current yaw angle of the gyro
     * @return the yaw angle of the gyro (between -180 and 180)
     */
    @Override
    public double getAngle() {
        return yaw;
    }
    
    /**
     * Sets the yaw, for testing purposes
     * @param yaw the new yaw value
     */
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }
    
    /**
     * Gets the Y velocity of the simulated gyro
     * Units are m/s, unfortunately
     * @return the simulated y velocity of the gyro
     */
    @Override
    public double getVelocity() {
        return yVelocity;
    }

    /**
     * Sets the Y velocity of the simulated gyro
     * @param velocity the velocity you want the gyro to have
     */
    public void setVelocity(double velocity) {
        yVelocity = velocity;
    }


    /** The stuff beneath this comment is here so no errors are thrown */
    @Override
    public void close() throws Exception {}

    @Override
    public void calibrate() {}

    @Override
    public double getRate() {return 0;}
}