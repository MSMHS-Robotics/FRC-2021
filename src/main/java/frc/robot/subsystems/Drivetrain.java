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

    private Object gyro;

    private Object leftEncoder;
    private Object rightEncoder;

    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    private DifferentialDrive drivetrain;
    
    private PIDController headingPID;

    /**
     * Creates a new drivetrain subsystem, which is kinda necesary to move
     * @param left1_p the port for the 1st left motor
     * @param left2_p the port for the 2nd left motor
     * @param left3_p the port for the 3rd left motor
     * @param right1_p the port for the 1st right motor
     * @param right2_p the port for the 2nd right motor
     * @param right3_p the port for the 3rd right motor
     */
    public Drivetrain(int left1_p, int left2_pm int left3_p, int right1_p, int right2_p, int right3_p, int leftEncoder1_p, int leftEncoder2_p, int rightEncoder1_p, int rightEncoder2_p) {
        if (Constants.unitTests) {
            left1 = new RocketSparkMAX_T(left1_p);
            left2 = new RocketSparkMAX_T(left2_p);
            left3 = new RocketSparkMAX_T(left3_p);
            right1 = new RocketSparkMAX_T(right1_p);
            right2 = new RocketSparkMAX_T(right2_p);
            right3 = new RocketSparkMAX_T(right3_p);

            gyro = new RocketGyro_T(); // not sure how useful this'll be

            leftEncoder = new RocketEncoder_T(leftEncoder1_p, leftEncoder2_p); // or what the heck these'll do
            rightEncoder = new RocketEncoder_T(rightEncoder1_p, rightEncoder2_p);
        } else {
            left1 = new RocketSparkMAX(left1_p);
            left2 = new RocketSparkMAX(left2_p);
            left3 = new RocketSparkMAX(left3_p);
            right1 = new RocketSparkMAX(right1_p);
            right2 = new RocketSparkMAX(right2_p);
            right3 = new RocketSparkMAX(right3_p);

            gyro = new AHRS(SPI.Port.kMXP); // this is one port that def won't change

            leftEncoder = new Encoder(leftEncoder1_p, leftEncoder2_p);
            rightEncoder = new Encoder(rightEncoder1_p, rightEncoder2_p);
        }

        leftSide = new SpeedControllerGroup(left1, left2, left3); // this _should_ work because RocketSparkMAX implements SpeedController
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        diffDrive = new DifferentialDrive(leftSide, rightSide);

        turningPID = new PIDController(Constants.turningPID.kP, Constants.turningPID.kI, Constants.turningPID.kD);
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
     * turns the robot in-place to a given angle
     * @param angle the angle you want to turn to (relative to robot starting orientation)
     * @return a boolean representing if the robot has reached the angle or not
     */
    public boolean turnToHeading(double angle) {
        diffDrive.arcadeDrive(0, headingPID.calculate(gyro.getYaw(), angle));
        return turningPID.atSetpoint();
    }

    /**
     * Reset the turningPID for some reason
     */
    public void turningPIDReset() {
        turningPID.reset();
    }

    /**
     * Reset all of the encoders on the drivetrain
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
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