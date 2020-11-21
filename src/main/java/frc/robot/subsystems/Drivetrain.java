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

    private SpeedControllerGroup leftSide = new SpeedControllerGroup(left1, left2, left3);
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(right1, right2, right3);
    private DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);
    
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
    }

    public void drive()

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