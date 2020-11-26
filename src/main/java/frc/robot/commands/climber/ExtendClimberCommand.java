package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimberCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Climber climber;

    /**
     * Creates a new ExtendClimberCommand command
     * @param climber a climber subsystem to be used by this command
     */
    public ExtendClimberCommand(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.extendClimber(); // set the extend flag
    }

    @Override
    public boolean isFinished() {
        return climber.isClimberExtended(); // if it's up against the limit switch
    }
}