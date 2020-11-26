package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class VisionAlignCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Drivetrain drivetrain;
    private final Vision vision;

    /**
     * Creates a new VisionAlignCommand command This command turns the robot in
     * place to align with the vision target, if there is a vision target in sight
     * 
     * @param drivetrain a drivetrain subsystem to be used by this command
     * @param vision a vision subsystem to be used by this command
     */
    public VisionAlignCommand(Drivetrain drivetrain, Vision vision) {
        this.drivetrain = drivetrain;
        this.vision = vision;
        addRequirements(drivetrain, vision);
    }

    @Override
    public void execute() {
        drivetrain.visionAlign(vision.getXOffset());
    }

    /** Stop if we're aligned */
    @Override
    public boolean isFinished() {
        return vision.isAligned();
    }
}