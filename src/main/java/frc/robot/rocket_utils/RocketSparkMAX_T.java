package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketSparkMAX_T {
    /** Public so we can read while testing */
    private double power = 0;
    private int port;

    /** Creates a new RocketSparkMAX_T
     *  implements something that looks like a SparkMAX but doesn't actaully do anything.
     *  This allows us to run it in the virtual
     */
    public RocketSparkMAX_T(int port) {
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