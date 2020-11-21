package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketSparkMAX {
    private CANSparkMAX motor;

    /** Creates a new RocketSparkMAX
     *  pretty much just a regular CANSparkMAX, except has a few extras to make better
     */
    public RocketSparkMAX(int port) {
        motor = new CANSparkMAX(port, MotorType.kBrushless);
    }

    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    public double get() {
        if (motor != null) {
            return motor.get();
        } else {
            return 0.0;
        }
    }

    public CANSparkMAX getMotorInstance() {
        return motor;
    }

    public boolean isMotorNotNull() {
        return motor != null;
    }
}