package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketTalon;
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
    
    public Drivetrain(int left1_p, int left2_pm int left3_p, int right1_p, int right2_p, int right3_p) {
        if (Constants.unitTests) {
        } else {
        }
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