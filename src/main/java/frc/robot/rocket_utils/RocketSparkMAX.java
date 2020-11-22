package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketSparkMAX implements SpeedController {
    private CANSparkMAX motor;

    /** 
     * Creates a new RocketSparkMAX
     * pretty much just a regular CANSparkMAX, except has a few extras to make better
     * The main thing it has are the null checks at every turn so we don't have to clutter our main code
     * with them and so we don't get yelled at a lot by the DS when a CAN cable gets unplugged
     */
    public RocketSparkMAX(int port) {
        motor = new CANSparkMAX(port, MotorType.kBrushless);
    }

    /**
     * Sets the motor to the given power
     * @return the speed you want to run the motor at
     */
    @Override
    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    /**
     * Gets the last set power of the motor
     * @return the current speed the motor is set at
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
     * sets whether the motor is inverted or not
     * This is equivalent to multiplying all subsequent calls to set() by -1
     * @param isInverted whether the motor should be inverted or not, true being inverted
     */
    @Override
    public void setInverted(boolean isInverted) {
        if (motor != null) {
            motor.setInverted(isInverted);
        }
    }

    /**
     * Gets whether the motor has been set to be inverted or not
     * True means it has been
     * @return a boolean indicating whether the motor is inverted or not
     */
    @Override
    public Boolean getInverted() {
        if (motor != null) {
            return motor.getInverted();
        } else {
            return false;
        }
    }

    /** Disables the motor. idk what this does */
    @Override
    public void disbale() {
        if (motor != null) {
            motor.disable();
        }
    }

    /** Stops the motor */
    @Override
    public void stopMotor() {
        if (motor != null) {
            motor.stopMotor();
        }
    }

    /**
     * Gets the motor instance being used by this class
     * This might or might not be needed for the CANEncoders to work
     * @return the CANSparkMAX instance used by this class
     */
    public CANSparkMAX getMotorInstance() {
        return motor;
    }

    /**
     * Returns whether the motor is null or not
     * Seeing as the point of this class is to prevent NullPointerExceptions at every turn,
     * we need some way to know if the CAN wires have come unplugged
     */
    public boolean isMotorNotNull() {
        return motor != null;
    }
}