package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketDigitalInput;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.rocket_utils.RocketTalon_T;
import frc.robot.Constants;

/**
 * A subsystem to control the indexer/pass-through
 */
public class Climber extends SubsystemBase {
    private RocketMotor climberMotor;
    private RocketMotor extendMotor;
    private RocketDigitalInput limitSwitch;

    public Climber(int climber_p, int climberExtend_p, int limitSwitch_p) {
        if (Constants.unitTests) {
            climberMotor = new RocketTalon_T(climber_p);
            extendMotor = new RocketTalon_T(climberExtend_p);
            limitSwitch = new RocketLimitSwitch_T(limitSwitch_p);
        } else {
            climberMotor = new RocketTalon(climber_p);
            extendMotor = new RocketTalon(climberExtend_p);
            limitSwitch = new DigitalInput(limitSwitch_p);
        }
    }

  /**
   * Sets the climber to a given speed
   * @param power the speed at which you want the climber to run (negative means reverse)
   */
  public void setClimber(double power) {
    climberMotor.set(power); // != null is handled inside RocketUtils
  }

  public void extend() {
      extendMotor.set(1);
  }

  /**
   * Resets the climber subsystem to its pre-start state
   */
  public void reset() {
    climberMotor.set(0);
    extendMotor.set(0);
  }

  /**
   * the required isGood() method
   * here we only have one motor so it's pretty easy
   * @return a Boolean representing if the motors can be reached or not
   */
  public Boolean isGood() {
    return climberMotor.isMotorNotNull() && extendMotor.isMotorNotNull(); // RocketUtils at work
  }

  @Override
  public void periodic() {
  }
}
