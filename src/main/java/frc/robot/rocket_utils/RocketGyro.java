package frc.robot.rocket_utils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** An actual gyro (specifically a navX) */
public class RocketGyro implements Gyro {
    private AHRS gyro;

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
    }

    /**
     * Gets the current yaw angle of the gyro
     * @return the yaw angle of the gyro (between -180 and 180)
     */
    @Override
    public double getAngle() {
        return gyro.getAngle() % 360 - 180; // scale it so we stay in -180 to 180 with wrapparound
    }

    /**
     * Gets the current Y velocity of the gyro
     * This is just accleration integrated into velocity so it might be pretty noisy
     * Maybe just integrate it ourselves with a filter?
     * @return the Y velocity of the gyro
     */
    public double getVelocity() {
        return gyro.getVelocityY(); // Experimental, probably not very accurate
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