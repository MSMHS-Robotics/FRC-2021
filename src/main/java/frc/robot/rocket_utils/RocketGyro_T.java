package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.interfaces.Gyro;

/** A simulated gyro (specifically a navX) */
public class RocketGyro_T implements Gyro {
    public double yaw = 0;

    /** Creates a new RocketGyro */
    public RocketGyro_T() {
    }
    
    /**
     * Gets the current heading/angle
     * ABSOLUTELY no idea how this is going to work in simulation
     * @return the current rotation around the Z axis
     */
    public double getYaw() {
        return yaw;
    }

    /**
     * resets the gyro
     */
    @Override
    public void reset() {
        yaw = 0;
    }

    
    
    
    /** The stuff beneath this comment is here so no errors are thrown */
    @Override
    public double getAngle() {
        // TODO Auto-generated method stub
        return yaw;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void calibrate() {
        // TODO Auto-generated method stub
    }

    @Override
    public double getRate() {
        // TODO Auto-generated method stub
        return 0;
    }
}