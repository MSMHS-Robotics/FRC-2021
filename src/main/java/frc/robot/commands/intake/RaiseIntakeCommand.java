package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RaiseIntakeCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake intake;

    /**
     * Creates a new RaiseIntakeCommand command. Sets the intake to be raised in intake's periodic
     * @param intake an intake subsystem to be used by this command
     */
    public RaiseIntakeCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.raiseIntake();
    }

    @Override
    public boolean isFinished() {
        return intake.isIntakeRaised();
    }
}