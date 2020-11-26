package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class WarmUpAutoCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Shooter shooter;
    private final Vision vision;
    private Double rpm;
    private boolean isDone = false;

    /**
     * Creates a new WarmUpAutoCommand command
     * This is the same as {@link WarmUpCommand}, except it takes a value that it warms up to
     * instead of being passed a joystick
     * 
     * @param shooter a shooter subsystem to be used by this command
     * @param rpm the speed you want to warm up to
     * @param vision a vision subsystem used by this command
     */
    public WarmUpAutoCommand(Shooter shooter, Double rpm, Vision vision) {
        this.shooter = shooter;
        this.vision = vision;
        this.rpm = rpm;
        addRequirements(shooter, vision);
    }

    @Override
    public void execute() {
        isDone = shooter.warmUp(rpm);
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
}