package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketTalon {
    private WPI_TalonSRX motor;

    /** Creates a new RocketTalon
     *  pretty much just a regular WPI_TalonSRX, except has a few extras to make better
     */
    public RocketTalon(int port) {
        motor = new WPI_TalonSRX(port);
    }

    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    public boolean isMotorNotNull() {
        return motor != null;
    }
}