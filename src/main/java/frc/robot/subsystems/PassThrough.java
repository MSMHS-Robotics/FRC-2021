package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * A subsystem to contorol the indexer/pass-through
 */
public class PassThrough extends SubsystemBase {
  private WPI_TalonSRX passThroughMotor;        
  
  /**
   * Creates a new PassThrough.
   */
  public PassThrough(int passThrough_p) {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
