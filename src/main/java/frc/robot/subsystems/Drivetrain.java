package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketSparkMAX;
import frc.robot.rocket_utils.RocketSparkMAX_T;
import frc.robot.Constants;

/**
 * A subsystem to control the drivetrain
 */
public class Drivetrain extends SubsystemBase {
    private Object left1;
    private Object left2;
    private Object left3;
    private Object right1;
    private Object right2;
    private Object right3;

    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    private DifferentialDrive drivetrain;
    
    /**
     * Creates a new drivetrain subsystem, which is kinda necesary to move
     * @param left1_p the port for the 1st left motor
     * @param left2_p the port for the 2nd left motor
     * @param left3_p the port for the 3rd left motor
     * @param right1_p the port for the 1st right motor
     * @param right2_p the port for the 2nd right motor
     * @param right3_p the port for the 3rd right motor
     */
    public Drivetrain(int left1_p, int left2_pm int left3_p, int right1_p, int right2_p, int right3_p) {
        if (Constants.unitTests) {
            left1 = new RocketSparkMAX_T(left1_p);
            left2 = new RocketSparkMAX_T(left2_p);
            left3 = new RocketSparkMAX_T(left3_p);
            right1 = new RocketSparkMAX_T(right1_p);
            right2 = new RocketSparkMAX_T(right2_p);
            right3 = new RocketSparkMAX_T(right3_p);
        } else {
            left1 = new RocketSparkMAX(left1_p);
            left2 = new RocketSparkMAX(left2_p);
            left3 = new RocketSparkMAX(left3_p);
            right1 = new RocketSparkMAX(right1_p);
            right2 = new RocketSparkMAX(right2_p);
            right3 = new RocketSparkMAX(right3_p);
        }

        leftSide = new SpeedControllerGroup(left1, left2, left3); // this _should_ work because RocketSparkMAX implements SpeedController
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        diffDrive = new DifferentialDrive(leftSide, rightSide);
    }

    /**
     * The actual function that drives us. This is a tank-drive configuration
     * @param leftStickY the Y value of the left joystick
     * @param rightStickY the Y value of the right joystick
     */
    public void drive(double leftStickY, double rightStickY) {
        diffDrive.tankDrive(leftStickY, rightStickY, true); // "true" to square inputs in the diff drive
    }

    /**
     * the required isGood() method
     * @return if the subsystem is functioning ("good") or not
     */
    public Boolean isGood() {
        return true;
    }

    @Override
    public void periodic() {
    }
}