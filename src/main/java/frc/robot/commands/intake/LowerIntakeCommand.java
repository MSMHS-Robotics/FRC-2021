package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerIntakeCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake intake;

    /**
     * Creates a new LowerIntakeCommand command
     * @param intake an intake subsystem to be used by this command
     */
    public LowerIntakeCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.lowerIntake();
    }

    @Override
    public boolean isFinished() {
        return !intake.isIntakeRaised();
    }
}