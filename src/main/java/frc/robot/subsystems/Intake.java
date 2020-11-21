package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.Constants;

/**
 * A subsystem to control the intake
 */
public class Intake extends SubsystemBase {
    private Object motor;
    private Object positionMotor;
    private Boolean intakeRaised = true;

    /**
     * Creates a new Intake
     * The intake controls the intake-y parts (position and intake/outake)
     * @param intake_p the intake motor port
     * @param intakePosition_p the intake position motor port
     */
    public Intake(int intake_p, int intakePosition_p) {
        if (Constants.unitTests) {
            motor = new RocketTalon_T(intake_p);
            positionMotor = new RocketTalon_T(intakePosition_p);
        } else {
            motor = new RocketTalon(intake_p);
            positionMotor = new RocketTalon(intakePosition_p);
        }
    }

    /**
     * lowers the intake
     */
    public void lowerIntake() {
        intakeRaised = false;
    }

    /**
     * raises the intake
     */
    public void raiseIntake() {
        intakeRaised = true;
    }

    /**
     * returns whether or not the intake is raised
     * @return if the intake is raised or not
     */
    public Boolean isIntakeRaised() {
        return intakeRaised;
    }

    /**
     * sets the intake motor to a given power
     * @param power the speed at which you want to run the intake (negative is reverse)
     */
    public void setIntake(double power) {
        motor.set(power);
    }

    /**
     * returns the last power the motor was set to
     * @return the current speed the motor is set to
     */
    public double getIntakeSpeed() {
        return motor.get();
    }

    @Override
    public void periodic() {
    }
}