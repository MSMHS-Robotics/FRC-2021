package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketSparkMAX_T implements RocketMotor {
    /** Public so we can read while testing */
    private double power = 0;
    private int port;
    private boolean inverted = false;

    /** 
     * Creates a new RocketSparkMAX_T
     *  implements something that looks like a SparkMAX but doesn't actaully do anything.
     *  This allows us to run it in the virtual
     */
    public RocketSparkMAX_T(int port) {
        this.port = port;
    }

    /**
     * Sets the motor to run at a certain speed
     * @param power the speed you want to run the motor at
     */
    @Override
    public void set(double power) {
        this.power = MathUtil.clamp(power, -1.0, 1.0);
        if (inverted) {
            this.power *= -1;
        }
    }

    /**
     * Returns the last speed the motor was set at
     * @return the last power the motor was set to
     */
    @Override
    public double get() {
        return power;
    }

    /**
     * sets whether the motor is inverted or not.
     * This is useful when running a drivetrain, because forwards for the left
     * would be backwards for the right because the motors are facing opposite directions
     * @param isInverted whether the motor is inverted, true being inverted
     */
    @Override
    public void setInverted(boolean isInverted) {
        inverted = isInverted;
    }

    /**
     * returns whether the motor is inverted or not
     * @return if the motor is inverted or not
     */
    @Override
    public boolean getInverted() {
        return inverted;
    }

    /**
     * Not really sure what this is supposed to do but we have to implement it
     * because of implementing the SpeedController interface
     */
    @Override
    public void disable() {
        power = 0;
    }

    /**
     * stops the motor, hence the name
     */
    @Override
    public void stopMotor() {
        power = 0;
    }
    
    /**
     * Returns the port that was passed to the constructor
     * This should be checked against Constants.java in unit tests to make sure they match
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns whether the motor is *not* null
     * This is useful since we're suppressing null warnings in the other RocketSparkMAX class
     * And still want to know (in a less crash-and-burn-y way) if the motor is unplugged or not
     * @return whether the motor exists or not
     */
    public boolean isMotorNotNull() {
        return true;
    }

    /** Ignore this, necessary to not get an error */
    @Override
    public void pidWrite(double output) {
    }
}