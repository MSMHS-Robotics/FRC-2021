package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimbCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Climber climber;

    /**
     * Creates a new ClimbCommand command
     * @param climber a climber subsystem to be used by this command
     */
    public ClimbCommand(Climber climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.setClimber(1); // just straight-up full-power ramming-speed it
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}