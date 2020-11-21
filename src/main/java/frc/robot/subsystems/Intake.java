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

    public Intake(int intake_p, int intakePosition_p) {
        if (Constants.unitTests) {
            motor = new RocketTalon_T(intake_p);
            positionMotor = new RocketTalon_T(intakePosition_p);
        } else {
            motor = new RocketTalon(intake_p);
            positionMotor = new RocketTalon(intakePosition_p);
        }
    }

    public void lowerIntake() {
        intakeRaised = false;
    }

    public void raiseIntake() {
        intakeRaised = true;
    }

    public Boolean isIntakeRaised() {
        return intakeRaised;
    }

    public void setIntake(double power) {
        motor.set(power);
    }

    @Override
    public void periodic() {
    }
}