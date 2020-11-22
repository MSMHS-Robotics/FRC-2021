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

    private Object left1Encoder;
    private Object left2Encoder;
    private Object left3Encoder;
    private Object right1Encoder;
    private Object right2Encoder;
    private Object right3Encoder;


    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    private DifferentialDrive drivetrain;
    
    private PIDController turningPID;
    private PIDController distancePID;

    /**
     * Creates a new drivetrain subsystem, which is kinda necesary to move
     * @param left1_p the port for the 1st left motor
     * @param left2_p the port for the 2nd left motor
     * @param left3_p the port for the 3rd left motor
     * @param right1_p the port for the 1st right motor
     * @param right2_p the port for the 2nd right motor
     * @param right3_p the port for the 3rd right motor
     * @param leftEncoder1_p the 1st port for the left through-bore encoder
     * @param leftEncoder2_p the 2nd port for the left though-bore encoder
     * @param rightEncoder1_p the 1st port for the right though-bore encoder
     * @param rightEncoder2_p the 2nd port for the right though-bore encoder
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

            left1Encoder = new RocketCANEncoder_T(left1); // _this_ part can only end in misery and suffering
            left2Encoder = new RocketCANEncoder_T(left2); // and utter destruction and pain and hate and ...
            left3Encoder = new RocketCANEncoder_T(left3);
            right1Encoder = new RocketCANEncoder_T(right1);
            right2Encoder = new RocketCANEncoder_T(right2);
            right3Encoder = new RocketCANEncoder_T(right3);
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

            left1Encoder = new CANEncoder(left1);
            left2Encoder = new CANEncoder(left2);
            left3Encoder = new CANEncoder(left3);
            right1Encoder = new CANEncoder(right1);
            right2Encoder = new CANEncoder(right2);
            right3Encoder = new CANEncoder(right3);
        }

        left1Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);
        left2Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);
        left3Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);
        right1Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);
        right2Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);
        right3Encoder.setPositionConversionFactor(Constants.encoderScaleFactor);


        leftSide = new SpeedControllerGroup(left1, left2, left3); // this _should_ work because RocketSparkMAX implements SpeedController
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        diffDrive = new DifferentialDrive(leftSide, rightSide);

        turningPID = new PIDController(Constants.turningPID.kP, Constants.turningPID.kI, Constants.turningPID.kD);
        distancePID = new PIDController(Constants.distancePID.kP, Constants.distancePID.kI, Constants.distancePID.kD);
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
    public boolean turnToAngle(double angle) {
        diffDrive.arcadeDrive(0, headingPID.calculate(gyro.getYaw(), angle));
        return turningPID.atSetpoint();
    }

    /**
     * drives the robot to a given distance on a given heading
     * @param distance the distance, in ticks, you want to go
     * @param lastHeading the last taken heading before starting the command, so you drive straight
     * @return a bool representing whether we're there yet or not
     */
    public boolean driveDistance(double distance, double lastHeading) {
        diffDrive.arcadeDrive(distancePID.calculate(getEncoderAverage(), distance), turningPID.calculate(gyro.getYaw(), lastHeading));
        return distancePID.atSetpoint();
    }

    /**
     * Reset the turningPID for some reason
     */
    public void turningPIDReset() {
        turningPID.reset();
    }

    /**
     * A function to reset the distancePID
     */
    public void distancePIDReset() {
        distancePID.reset();
    }

    /**
     * Reset all of the encoders on the drivetrain
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();

        left1Encoder.setPosition(0);
        left2Encoder.setPosition(0);
        left3Encoder.setPosition(0);
        right1Encoder.setPosition(0);
        right2Encoder.setPosition(0);
        right3Encoder.setPosition(0);
    }

    /**
     * a function to reset the gyro, for use by commands
     */
    public void resetGyro() {
        gyro.reset();
    }

    /**
     * A function that resets pretty much everything. Probably best practice to run this before a match
     * Also sets all motors to 0
     */
    public void hardReset() {
        resetEncoders();
        turningPIDReset();
        distancePIDReset();
        resetGyro();
        drive(0, 0);
    }

    /**
     * A method/function/whatever to get an average of all of the encoder readings
     * @return an average of all of the encoder readins on the drivetrain
     */
    public double getEncoderAverage() {
        double leftSide = (left1Encoder.getPosition() + left2Encoder.getPosition() + left3Encoder.getPosition() + leftEncoder.get()) / 4;
        double rightSide = (right1Encoder.getPosition() + right2Encoder.getPosition() + right3Encoder.getPosition() + rightEncoder.get()) / 4;
        return (leftSide + rightSide) / 2;
    }

    /**
     * A method to get the heading the robot is pointing at
     * @return the current heading
     */
    public double getHeading() {
        return gyro.getYaw();
    }

    /**
     * the required isGood() method
     * Checks all motors to see if they exist
     * @return if the subsystem is functioning ("good") or not
     */
    public Boolean isGood() {
        boolean leftSideGood = left1.isMotorNotNull() && left2.isMotorNotNull() && left3.isMotorNotNull();
        boolean rightSideGood = right1.isMotorNotNull() && right1.isMotorNotNull() && right1.isMotorNotNull();
        return leftSideGood && rightSideGood;
    }

    @Override
    public void periodic() {
    }
}