package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketCANEncoder;
import frc.robot.rocket_utils.RocketCANEncoderInterface;
import frc.robot.rocket_utils.RocketCANEncoder_T;
import frc.robot.rocket_utils.RocketEncoder_T;
import frc.robot.rocket_utils.RocketGyro;
import frc.robot.rocket_utils.RocketGyro_T;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketSparkMAX;
import frc.robot.rocket_utils.RocketSparkMAX_T;
import frc.robot.Constants;

/**
 * A subsystem to control the drivetrain
 */
public class Drivetrain extends SubsystemBase {
    private RocketMotor left1;
    private RocketMotor left2;
    private RocketMotor left3;
    private RocketMotor right1;
    private RocketMotor right2;
    private RocketMotor right3;

    private RocketGyro gyro;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private RocketCANEncoderInterface left1Encoder;
    private RocketCANEncoderInterface left2Encoder;
    private RocketCANEncoderInterface left3Encoder;
    private RocketCANEncoderInterface right1Encoder;
    private RocketCANEncoderInterface right2Encoder;
    private RocketCANEncoderInterface right3Encoder;

    private SpeedControllerGroup leftSide;
    private SpeedControllerGroup rightSide;
    private double leftStickY = 0;
    private double rightStickY = 0;
    private DifferentialDrive diffDrive;
    
    private PIDController turningPID;
    private PIDController distancePID;
    private PIDController alignPID;

    private ShuffleboardTab tab = Shuffleboard.getTab("tab");
    private NetworkTableEntry sb_status = tab.add("Status", false).getEntry();
    private NetworkTableEntry sb_gyro = tab.add("Heading", 0).getEntry();
    private NetworkTableEntry sb_velocity = tab.add("Velocity", 0).getEntry();
    private NetworkTableEntry sb_gyroReset = tab.add("Reset Gyro", false).getEntry();
    private NetworkTableEntry sb_encoderReset = tab.add("Reset Encoders", false).getEntry();
    private NetworkTableEntry sb_leftY = tab.add("Left Stick Y", 0).getEntry();
    private NetworkTableEntry sb_rightY = tab.add("Right Stick Y", 0).getEntry();
    
    private NetworkTableEntry sb_distance = tab.add("Encoder Distance", 0).getEntry();
    private NetworkTableEntry sb_leftSpeed = tab.add("Left Side Speed", 0).getEntry();
    private NetworkTableEntry sb_rightSpeed = tab.add("Right Side Speed", 0).getEntry();
    private NetworkTableEntry sb_resetSubsystem = tab.add("Reset Drivetrain", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    private NetworkTableEntry sb_resetAll = tab.add("Hard-Reset Everything", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    private NetworkTableEntry debugButton = tab.add("Debug Mode?", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    
    /**
     * Creates a new drivetrain subsystem, which is kinda needed to move
     */
    public Drivetrain(boolean unitTests) {
        if (unitTests) {
            left1 = new RocketSparkMAX_T(Constants.left1_p);
            left2 = new RocketSparkMAX_T(Constants.left2_p);
            left3 = new RocketSparkMAX_T(Constants.left3_p);
            right1 = new RocketSparkMAX_T(Constants.right1_p);
            right2 = new RocketSparkMAX_T(Constants.right2_p);
            right3 = new RocketSparkMAX_T(Constants.right3_p);

            gyro = new RocketGyro_T(Port.kMXP); // not sure how useful this'll be

            leftEncoder = new RocketEncoder_T(Constants.leftEncoder1_p, Constants.leftEncoder2_p); // or what the heck these'll do
            rightEncoder = new RocketEncoder_T(Constants.rightEncoder1_p,Constants.rightEncoder2_p);

            left1Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) left1); // _this_ part can only end in misery and suffering
            left2Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) left2); // and utter destruction and pain and hate and ...
            left3Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) left3);
            right1Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) right1);
            right2Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) right2);
            right3Encoder = new RocketCANEncoder_T((RocketSparkMAX_T) right3);
        } else {
            left1 = new RocketSparkMAX(Constants.left1_p);
            left2 = new RocketSparkMAX(Constants.left2_p);
            left3 = new RocketSparkMAX(Constants.left3_p);
            right1 = new RocketSparkMAX(Constants.right1_p);
            right2 = new RocketSparkMAX(Constants.right2_p);
            right3 = new RocketSparkMAX(Constants.right3_p);

            gyro = new RocketGyro(Port.kMXP); // this is one port that def won't change

            leftEncoder = new Encoder(Constants.leftEncoder1_p, Constants.leftEncoder2_p);
            rightEncoder = new Encoder(Constants.rightEncoder1_p, Constants.rightEncoder2_p);

            left1Encoder = new RocketCANEncoder((CANSparkMax) left1);
            left2Encoder = new RocketCANEncoder((CANSparkMax) left2);
            left3Encoder = new RocketCANEncoder((CANSparkMax) left3);
            right1Encoder = new RocketCANEncoder((CANSparkMax) right1);
            right2Encoder = new RocketCANEncoder((CANSparkMax) right2);
            right3Encoder = new RocketCANEncoder((CANSparkMax) right3);
        }

        left1Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);
        left2Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);
        left3Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);
        right1Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);
        right2Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);
        right3Encoder.setPositionConversionFactor(Constants.canEncoderScaleFactor);

        leftEncoder.setDistancePerPulse(Constants.encoderScaleFactor);
        rightEncoder.setDistancePerPulse(Constants.encoderScaleFactor);


        leftSide = new SpeedControllerGroup(left1, left2, left3); // this _should_ work because RocketSparkMAX implements SpeedController
        rightSide = new SpeedControllerGroup(right1, right2, right3);
        diffDrive = new DifferentialDrive(leftSide, rightSide);

        turningPID = new PIDController(Constants.turningPID.kP, Constants.turningPID.kI, Constants.turningPID.kD);
        distancePID = new PIDController(Constants.distancePID.kP, Constants.distancePID.kI, Constants.distancePID.kD);
        alignPID = new PIDController(Constants.alignPID.kP, Constants.alignPID.kI, Constants.alignPID.kD);
    }

    /**
     * The actual function that drives us. This is a tank-drive configuration
     * @param leftStickY the Y value of the left joystick
     * @param rightStickY the Y value of the right joystick
     */
    public void drive(double leftStickY, double rightStickY) {
        this.leftStickY = leftStickY;
        this.rightStickY = rightStickY;
        diffDrive.tankDrive(leftStickY, rightStickY, true); // "true" to square inputs in the diff drive
    }

    /**
     * turns the robot in-place to a given angle
     * @param angle the angle you want to turn to (relative to robot starting orientation)
     * @return a boolean representing if the robot has reached the angle or not
     */
    public boolean turnToAngle(double angle) {
        diffDrive.arcadeDrive(0, turningPID.calculate(gyro.getAngle(), angle));
        return turningPID.atSetpoint();
    }

    /**
     * drives the robot to a given distance on a given heading
     * @param distance the distance, in ticks, you want to go
     * @param lastHeading the last taken heading before starting the command, so you drive straight
     * @return a bool representing whether we're there yet or not
     */
    public boolean driveDistance(double distance, double lastHeading) {
        diffDrive.arcadeDrive(distancePID.calculate(getEncoderAverage(), distance), turningPID.calculate(gyro.getAngle(), lastHeading));
        return distancePID.atSetpoint();
    }

    public void visionAlign(double xOffset) {
        diffDrive.arcadeDrive(0, alignPID.calculate(xOffset, 0), false);
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
        return gyro.getAngle(); // possibly change to getAngle() and then make sure to wrap to zero?
    }

    /**
     * Gets the velocity in the forwards direction read by the gyro
     * @return our speed in the forwards direction, negative is backwards
     */
    public double getVelocity() {
        return gyro.getVelocity();
    }

    /**
     * Gets the speed of the left side of the drivetrain, as read by the encoders
     * @return the speed of the left side of the drivetrain, negative is reverse
     */
    public double getLeftSpeed() {
        return (left1Encoder.getVelocity() + left2Encoder.getVelocity() + left3Encoder.getVelocity() + leftEncoder.getRate());
    }

    /**
     * Gets the speed of the right side of the drivetrain, as read by the encoders
     * @return the speed of the right side of the drivetrain, negative is reverse
     */
    public double getRightSpeed() {
        return (right1Encoder.getVelocity() + right1Encoder.getVelocity() + right1Encoder.getVelocity() + rightEncoder.getRate());
    }

    /**
     * Gets the last passed left stick Y value to the drive() function
     * @return the left stick Y value
     */
    public double getLeftStickY() {
        return leftStickY;
    }

    /**
     * Gets the last passed right stick Y value to the drive() function
     * @return the right stick Y value
     */
    public double getRightStickY() {
        return rightStickY;
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
        /**
         * Our shuffleboard values
         */
        sb_status.setBoolean(this.isGood());
        sb_gyro.setDouble(this.getHeading());
        sb_velocity.setDouble(this.getVelocity());
        sb_leftY.setDouble(this.getLeftStickY());
        sb_rightY.setDouble(this.getRightStickY());
        if(debugButton.getBoolean(false)) { // debug-mode information
            sb_distance.setDouble(this.getEncoderAverage());
            sb_leftSpeed.setDouble(this.getLeftSpeed());
            sb_rightSpeed.setDouble(this.getRightSpeed());
        }

        /** If the reset button is pressed */
        if (sb_gyroReset.getBoolean(false)) { // default value of not-pressed
            resetGyro(); // reset
            sb_gyroReset.setBoolean(false); // and turn button back off
        } 
        if (sb_encoderReset.getBoolean(false)) { // same thing here
            resetEncoders();
            sb_encoderReset.setBoolean(false);
        }

        /**
         * The subsystem reset button
         */
        if (sb_resetSubsystem.getBoolean(false)) { // default value of false
            hardReset(); // reset
            sb_resetSubsystem.setBoolean(false); // turn button back off
        }

        /**
         * The reset-all all button
         */
        if (sb_resetAll.getBoolean(false)) {
            hardReset();
            sb_resetAll.setBoolean(false);
        }
    }
}