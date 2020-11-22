package frc.robot.rocket_utils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** An actual gyro (specifically a navX) */
public class RocketGyro implements Gyro {
    private AHRS gyro;
    private double yaw;

    /** Creates a new RocketGyro */
    public RocketGyro(SPI.Port port) {
        gyro = new AHRS(port);
    }

    /**
     * resets the gyro
     */
    @Override
    public void reset() {
        gyro.reset();
        yaw = 0;
    }

    /**
     * Gets the current yaw angle of the gyro
     * @return the yaw angle of the gyro (between -180 and 180)
     */
    @Override
    public double getAngle() {
        return gyro.getAngle() % 360 - 180; // scale it so we stay in -180 to 180 with wrapparound
    }

    /** Everything below this line is so we don't get errors for not implementing stuffs */
    @Override
    public void close() throws Exception {
        gyro.close();
    }

    @Override
    public void calibrate() {gyro.calibrate();}

    @Override
    public double getRate() {return gyro.getRate();}
}