package frc.robot.rocket_utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class RocketSparkMAX implements RocketMotor {
    private CANSparkMax motor;

    /** 
     * Creates a new RocketSparkMAX
     * pretty much just a regular CANSparkMAX, except has a few extras to make better
     * The main thing it has are the null checks at every turn so we don't have to clutter our main code
     * with them and so we don't get yelled at a lot by the DS when a CAN cable gets unplugged
     */
    public RocketSparkMAX(int port) {
        motor = new CANSparkMax(port, MotorType.kBrushless);
    }

    /**
     * Sets the motor to run at a certain speed. Negative is reverse, range is from -1 to 1
     * 
     * @param power the speed you want to run the motor at
     */
    @Override
    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    /**
     * Returns the last speed the motor was set at
     * @return the last speed the motor was set to
     */
    @Override
    public double get() {
        if (motor != null) {
            return motor.get();
        } else {
            return 0.0;
        }
    }

    /**
     * sets whether the motor is inverted or not This is equivalent to multiplying
     * all subsequent calls to set() by -1
     * 
     * @param isInverted whether the motor should be inverted or not, true being inverted
     */
    @Override
    public void setInverted(boolean isInverted) {
        if (motor != null) {
            motor.setInverted(isInverted);
        }
    }

    /**
     * returns whether the motor is inverted or not
     * @return if the motor is inverted or not
     */
    public boolean getInverted() {
        if (motor != null) {
            return motor.getInverted();
        } else {
            return false;
        }
    }

    /**
     * Not really sure what this is supposed to do but we have to implement it
     * because of implementing the SpeedController interface
     */
    @Override
    public void disable() {
        if (motor != null) {
            motor.disable();
        }
    }

    /**
     * stops the motor, hence the name
     */
    @Override
    public void stopMotor() {
        if (motor != null) {
            motor.stopMotor();
        }
    }

    /**
     * Returns whether the motor is *not* null
     * This is useful since we're suppressing null warnings in the other RocketSparkMAX class
     * And still want to know (in a less crash-and-burn-y way) if the motor is unplugged or not
     * @return whether the motor exists or not
     */
    public boolean isMotorNotNull() {
        return motor != null;
    }

    /** Ignore this, necessary to not get an error */
    @Override
    public void pidWrite(double output) {
    }
}