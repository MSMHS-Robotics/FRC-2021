package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.interfaces.Gyro;

/** A simulated gyro (specifically a navX) */
public class RocketGyro_T implements Gyro {
    public double yaw = 0;

    /** Creates a new RocketGyro */
    public RocketGyro_T() {
    }    

    /**
     * resets the gyro
     */
    @Override
    public void reset() {
        yaw = 0;
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
    

    /** The stuff beneath this comment is here so no errors are thrown */
    @Override
    public void close() throws Exception {}

    @Override
    public void calibrate() {}

    @Override
    public double getRate() {return 0;}
}