package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /** 
     * Define the CAN ID for motors and such here. Makes it a little easier to manage
     */
    // drivetrain
    public static final int left1_p = 0;
    public static final int left2_p = 1;
    public static final int left3_p = 2;
    public static final int right1_p = 3;
    public static final int right2_p = 4;
    public static final int right3_p = 5;
    
    // intake + indexer
    public static final int intake_p = 6;
    public static final int intakePosition_p = 7;
    public static final int passThrough_p = 8;
    public static final int intakePositionPot_p = 1; // should be an analog channel
    
    // shooter
    public static final int shooter1_p = 9;
    public static final int shooter2_p = 10;
    public static final int trigger_p = 11;
    
    // climber
    public static final int climber_p = 12;
    public static final int climberPosition_p = 13;

    /**
     * Define the PWM port numbers for sensors and such here. Makes it a little easier to manage
     */
    public static final int leftEncoder1_p = 2;
    public static final int leftEncoder2_p = 3;
    public static final int rightEncoder1_p = 4;
    public static final int rightEncoder2_p = 5;

    /**
     * Define the scale factor for the CANEncoders
     */
    public static final double canEncoderScaleFactor = 1.0;
    public static final double encoderScaleFactor = 1.0;
    public static final double neoTicksPerRev = 100;
    public static final double wheelDiameter = 4.0;

    /**
     * This variable says whether we're running unit tests or not.
     * Setting it to true will make all of the subsystems use "fake" motors and stuff.
     */
    public static final Boolean unitTests = false;

    /** A class to hold the constants of the turning PID */
    public static final class turningPID {
        public static final double kP = 0.15;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    /** A class to hold the constants of the distance PID */
    public static final class distancePID {
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    /** A class to hold the constants of the shooter PID */
    public static final class shooterPID {
        public static final double kP = 0.00007;
        public static final double kI = 0.0000002;
        public static final double kD = 0.001;
        public static final double kFF = 0.000006;
    }

    /** A class to hold the constants of some intake stuff */
    public static final class intake {
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double fullRange = 100;
        public static final double offset = 50;
        public static final double intakeRaisedSetpoint = 5;
        public static final double intakeLoweredSetpoint = 0;
    }
}