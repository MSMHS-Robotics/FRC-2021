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
    
    // shooter
    public static final int shooter1_p = 9;
    public static final int shooter2_p = 10;
    public static final int trigger_p = 11;
    
    // climber
    public static final int climber_p = 12;
    public static final int climberPosition_p = 13;

    /**
     * This variable says whether we're running unit tests or not.
     * Setting it to true will make all of the subsystems use "fake" motors and stuff.
     */
    public static final Boolean unitTests = false;
}