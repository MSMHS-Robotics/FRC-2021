package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketTalon {
    private WPI_TalonSRX motor;

    /** Creates a new RocketTalon
     *  pretty much just a regular WPI_TalonSRX, except has a few extras to make better
     *  namely null checks at every turn so errors are exterminated and code is not crippled
     */
    public RocketTalon(int port) {
        motor = new WPI_TalonSRX(port);
    }

    /** 
     * Sets the motor to a given power
     * @param power the speed you want the motor to run at
     */
    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    /**
     * Gets the last set speed of the motor
     * @return the speed the motor is set at
     */
    public double get() {
        if (motor != null) {
            return motor.get();
        } else {
            return 0.0;
        }
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