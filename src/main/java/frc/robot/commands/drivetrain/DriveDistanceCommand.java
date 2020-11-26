package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveDistanceCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain drivetrain;
    private boolean isDone = false;
    private double lastHeading;
    private double distance;

    /**
     * Creates a new DriveDistance command
     * @param drivetrain a pass-through subsystem to be used by this command
     * @param distance the distance (in ticks) that you want to drive
     */
    public DriveDistanceCommand(Drivetrain drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;
        addRequirements(drivetrain);
    }

    /** Get the heading we started on, which is the one we want to keep */
    @Override
    public void initialize() {
        lastHeading = drivetrain.getHeading();
        drivetrain.resetEncoders();
    }

    /** drive on that heading until we reach a distance */
    @Override
    public void execute() {
        isDone = drivetrain.driveDistance(distance, lastHeading);
    }

    /** Keep going unless we've reached that distance */
    @Override
    public boolean isFinished() {
        return isDone;
    }
}