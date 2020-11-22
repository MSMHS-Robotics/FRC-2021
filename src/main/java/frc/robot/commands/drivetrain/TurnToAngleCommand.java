package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnToAngleCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain drivetrain;
    private boolean isDone = false;
    private double lastHeading;
    private double angle;

    /**
     * Creates a new TurnToAngleCommand command
     * This command turns the robot in place to a new angle, relative to where the robot
     * was facing before the command started
     * @param drivetrain a pass-through subsystem to be used by this command
     * @param angle the angle (relative) you want to turn to
     */
    public TurnToAngleCommand(Drivetrain drivetrain, double angle) {
        this.drivetrain = drivetrain;
        this.angle = angle;
        addRequirements(drivetrain);
    }

    /** Get the heading we started on, which we need to make this relative */
    @Override
    public void initialize() {
        lastHeading = drivetrain.getHeading();
    }

    /** turn to that angle */
    @Override
    public void execute() {
        isDone = drivetrain.turnToAngle(lastHeading - angle);
    }

    /** Keep going unless we've reached that angle */
    @Override
    public boolean isFinished() {
        return isDone;
    }
}