package frc.robot.rocket_utils;

import edu.wpi.first.wpiutil.math.MathUtil;

public class RocketSparkMAX implements SpeedController {
    private CANSparkMAX motor;

    /** Creates a new RocketSparkMAX
     *  pretty much just a regular CANSparkMAX, except has a few extras to make better
     *  The main thing it has are the null checks at every turn so we don't have to clutter our main code
     *  with them and so we don't get yelled at a lot by the DS when a CAN cable gets unplugged
     */
    public RocketSparkMAX(int port) {
        motor = new CANSparkMAX(port, MotorType.kBrushless);
    }

    @Override
    public void set(double power) {
        if (motor != null) {
            motor.set(power);
        }
    }

    @Override
    public double get() {
        if (motor != null) {
            return motor.get();
        } else {
            return 0.0;
        }
    }

    @Override
    public void setInverted(boolean isInverted) {
        if (motor != null) {
            motor.setInverted(isInverted);
        }
    }

    @Override
    public Boolean getInverted() {
        if (motor != null) {
            return motor.getInverted();
        } else {
            return false;
        }
    }

    @Override
    public void disbale() {
        if (motor != null) {
            motor.disable();
        }
    }

    @Override
    public void stopMotor() {
        if (motor != null) {
            motor.stopMotor();
        }
    }

    public CANSparkMAX getMotorInstance() {
        return motor;
    }

    public boolean isMotorNotNull() {
        return motor != null;
    }
}