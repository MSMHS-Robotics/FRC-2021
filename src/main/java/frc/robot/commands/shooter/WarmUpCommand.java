package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class WarmUpCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Shooter shooter;
    private Joystick joystick;
    private Vision vision;
    private boolean isDone = false;

    /**
     * Creates a new WarmUpCommand command
     * @param shooter a shooter subsystem to be used by this command
     */
    public WarmUpCommand(Shooter shooter, Vision vision, Joystick joystick) {
        this.shooter = shooter;
        this.vision = vision;
        this.joystick = joystick;
        addRequirements(shooter, vision);
    }

    @Override
    public void execute() {
        int dpad = joystick.getPOV();
        switch(dpad) {
            case 0:
                isDone = shooter.warmUp(Constants.ShooterPresets.layupRPM);
                break;
            case 90:
                isDone = shooter.warmUp(Constants.ShooterPresets.lineRPM);
                break;
            case 180:
                isDone = shooter.warmUp(Constants.ShooterPresets.trenchRPM);
                break;
            case 270:
                isDone = shooter.warmUp(Constants.ShooterPresets.maxRPM);
                break;
            default:
                isDone = shooter.warmUp(vision.getNeededRPM());
               break;
        }
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
}