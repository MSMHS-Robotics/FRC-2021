package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketTalon {
    /** Public so we can read while testing */
    private double power = 0;
    private int port;

    /** Creates a new RocketTalon
     *  implements something that looks like a Talon but doesn't actaully do anything.
     *  This allows us to run it in the virtual
     */
    public RocketTalon(int port) {
        this.port = port;
    }

    /** 
     * Sets the motor to a given power
     * @param power the speed you want the motor to run at
     */
    public void set(double power) {
        this.power = MathUtil.clamp(power, -1.0, 1.0);
    }

    /**
     * Gets the last set speed of the motor
     * @return the speed the motor is set at
     */
    public double get() {
        return power;
    }

    /**
     * Gets the port number passed in the constructor of this class
     * Useful for debugging/unit tests to verify no two motors have been assigned the same CAN ID
     * @return the port number of the motor
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns whether the motor is null or not
     * Seeing as the point of this class is to prevent NullPointerExceptions at every turn,
     * we need some way to know if the CAN wires have come unplugged
     */
    public boolean isMotorNotNull() {
        return true;
    }
}