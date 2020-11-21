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

    public void set(double power) {
        this.power = MathUtil.clamp(power, -1.0, 1.0);
    }

    public double getPower() {
        return power;
    }

    public int getPort() {
        return port;
    }

    public boolean isMotorNotNull() {
        return true;
    }
}