package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketCANEncoder_T;
import frc.robot.rocket_utils.RocketSparkMAX;
import frc.robot.rocket_utils.RocketSparkMAX_T;
import frc.robot.Constants;

/**
 * A subsystem to control the shooter + trigger
 */
public class Shooter extends SubsystemBase {
    private SpeedController shooterMotor;
    private SpeedController shooterFollowerMotor;
    private SpeedController triggerMotor;
    private Object shooterEncoder;

    private PIDController shooterPID;

    /**
     * Creates a new Shooter
     * @param shooter1_p the port for the first shooter motor
     * @param shooter2_p the port for the second/follower shooter motor
     * @param trigger_p the port for the trigger motor
     */
    public Shooter(int shooter1_p, int shooter2_p, int trigger_p) {
        if (Constants.unitTests) {
            shooterMotor = new RocketSparkMAX_T(shooter1_p);
            shooterFollowerMotor = new RocketSparkMAX_T(shooter2_p);
            triggerMotor = new RocketSparkMAX_T(trigger_p);

            shooterEncoder = new RocketCANEncoder_T(shooterMotor);
        } else {
            shooterMotor = new RocketSparkMAX(shooter1_p);
            shooterFollowerMotor = new RocketSparkMAX(shooter2_p);
            triggerMotor = new RocketSparkMAX(trigger_p);

            shooterEncoder = new CANEncoder(shooterMotor);
        }

        shooterPID = new PIDController(Constants.shooterPID.kP, Constants.shooterPID.kI, Constants.shooterPID.kD, Constants.shooterPID.kFF);
        shooterFollowerMotor.follow(shooterMotor, true); // this _hopefully_ doesn't break/fail miserably
    }

    /**
     * Runs the trigger motor at a given speed
     * @param power the speed at which to run the motor
     */
    public void setTrigger(double power) {
        triggerMotor.set(power);
    }

    /**
     * Resets the shooter encoders and control-y bits
     * Also sets all motors to 0
     */
    public void reset() {
        shooterEncoder.setPosition(0);
        shooterPID.reset();
        shooterMotor.set(0);
        shooterFollowerMotor.set(0);
        triggerMotor.set(0);
    }

    /**
     * Warms up the shooter motors to the desired RPM
     * @param RPM the speed (in rotations per minute) you want to shoot at
     * @return whether or not we are at that RPM
     */
    public boolean warmUp(double RPM) {
        shooterMotor.set(shooterPID.calculate(shooterEncoder.getVelocity(), RPM));
        return shooterPID.atSetpoint();
    }

    /**
     * the required isGood() method
     * Checks all motors to see if they exist
     * @return if the subsystem is functioning ("good") or not
     */
    public boolean isGood() {
        return shooterMotor.isMotorNotNull() && shooterFollowerMotor.isMotorNotNull() && triggerMotor.isMotorNotNull();
    }

    @Override
    public void periodic() {
    }
}