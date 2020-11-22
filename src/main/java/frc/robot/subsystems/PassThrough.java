package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.rocket_utils.RocketTalon_T;
import frc.robot.Constants;

/**
 * A subsystem to control the indexer/pass-through
 */
public class PassThrough extends SubsystemBase {
  private Object passThroughMotor;
  
  /**
   * Creates a new PassThrough.
   * @param passThrough_p the CAN ID/port of the pass-through motor. This should be specified in Constants.java
   */
  public PassThrough(int passThrough_p) {
    if (Constants.unitTests) {
      passThroughMotor = new RocketTalon_T(passThrough_p);
    } else {
      passThroughMotor = new RocketTalon(passThrough_p);
    }
  }

  /**
   * Sets the pass-through to a given speed
   * @param power the speed at which you want the pass-through to run (negative means reverse)
   */
  public void setPassThrough(double power) {
    passThroughMotor.set(power); // != null is handled inside RocketUtils
  }

  /**
   * Resets the pass-through subsystem to its pre-start state
   */
  public void reset() {
    passThroughMotor.set(0);
  }

  /**
   * the required isGood() method
   * here we only have one motor so it's pretty easy
   * @return a Boolean representing if the motor can be reached or not
   */
  public Boolean isGood() {
    return passThroughMotor.isMotorNotNull(); // RocketUtils at work
  }

  @Override
  public void periodic() {
  }
}
