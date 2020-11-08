package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.Constants;

/**
 * A subsystem to contorol the indexer/pass-through
 */
public class PassThrough extends SubsystemBase {
  private RocketTalon passThroughMotor;
  
  /**
   * Creates a new PassThrough.
   */
  public PassThrough(int passThrough_p) {
    passThroughMotor = new RocketTalon(passThrough_p, Constants.unitTests);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
