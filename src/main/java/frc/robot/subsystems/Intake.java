package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketTalon_T;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketPotentiometer_T;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.Constants;

/**
 * A subsystem to control the intake
 */
public class Intake extends SubsystemBase {
    private RocketMotor motor;
    private RocketMotor positionMotor;
    private Potentiometer positionPot;

    private PIDController intakePositionPID;

    /**
     * Creates a new Intake The intake controls the intake-y parts (position and
     * intake/outake)
     * 
     * @param intake_p            the intake motor port
     * @param intakePosition_p    the intake position motor port
     * @param intakePositionPot_p the analog port for the potentiometer attached to the intake
     */
    public Intake(int intake_p, int intakePosition_p, int intakePositionPot_p) {
        if (Constants.unitTests) {
            motor = new RocketTalon_T(intake_p);
            positionMotor = new RocketTalon_T(intakePosition_p);
            positionPot = new RocketPotentiometer_T(intakePositionPot_p, Constants.intake.fullRange, Constants.intake.offset);
        } else {
            motor = new RocketTalon(intake_p);
            positionMotor = new RocketTalon(intakePosition_p);
            positionPot = new AnalogPotentiometer(intakePositionPot_p, Constants.intake.fullRange, Constants.intake.offset);
        }

        intakePositionPID = new PIDController(Constants.intake.kP, Constants.intake.kI, Constants.intake.kD);
    }

    /**
     * Sets the setpoint of the intake pid to be lowered
     */
    public void lowerIntake() {
        intakePositionPID.setSetpoint(Constants.intake.intakeLoweredSetpoint);
    }

    /**
     * Sets the setpoint of the intake pid to be raised
     */
    public void raiseIntake() {
        intakePositionPID.setSetpoint(Constants.intake.intakeRaisedSetpoint);
    }

    /**
     * returns whether or not the intake is raised
     * checks the PID to see if we're there or not
     * @return if the intake is raised or not
     */
    public Boolean isIntakeRaised() {
        if (intakePositionPID.atSetpoint() && intakePositionPID.getSetpoint() == Constants.intake.intakeRaisedSetpoint) {
            return true;
        } else {
            return false;
        }
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

    /**
     * Resets everything of the intake
     * In this case all it does is set both motors to 0
     */
    public void reset() {
        motor.set(0);
        positionMotor.set(0);
    }

    /**
     * the required isGood() method
     * checks to see if both motors exist
     * @return if the subsystem is functioning ("good") or not
     */
    public Boolean isGood() {
        return motor.isMotorNotNull() && positionMotor.isMotorNotNull();
    }

    /** Sets the intake motor to PID to either raised or lowered and keep it there */
    @Override
    public void periodic() {
        positionMotor.set(intakePositionPID.calculate(positionPot.get()));
    }
}