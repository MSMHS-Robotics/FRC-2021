package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PassThrough;

public class SetIdleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PassThrough passThrough;

  /**
   * Creates a new SetIdle command
   * @param passThrough an intake subsystem to be used by this command
   */
  public SetIdleCommand(PassThrough passThrough) {
    this.passThrough = passThrough
    addRequirements(passThrough);
  }

  @Override
    public void execute() {
        passThrough.set(0.5);
    }

  @Override
  public boolean isFinished() {
    return true;
  }
}
