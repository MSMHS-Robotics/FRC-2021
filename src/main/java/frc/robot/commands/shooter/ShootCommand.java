package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

/** A command to shoot */
public class ShootCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Shooter shooter;
    private final Vision vision;
    private Joystick joystick;
    private boolean isDone = false;
    private boolean isWarmedUp = false;

    /**
     * Creates a new ShootCommand command. Warms up the shooter and then spins ("pulls") the trigger
     * 
     * @param shooter a shooter subsystem to be used by this command
     * @param vision a vision subsystem to be used by this command (for the distance to the goal)
     * @param joystick a joystick for this command, to get the d-pad stuff
     */
    public ShootCommand(Shooter shooter, Vision vision, Joystick joystick) {
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
                isWarmedUp = shooter.warmUp(Constants.ShooterPresets.layupRPM);
                break;
            case 90:
                isWarmedUp = shooter.warmUp(Constants.ShooterPresets.lineRPM);
                break;
            case 180:
                isWarmedUp = shooter.warmUp(Constants.ShooterPresets.trenchRPM);
                break;
            case 270:
                isWarmedUp = shooter.warmUp(Constants.ShooterPresets.maxRPM);
                break;
            default:
                isWarmedUp = shooter.warmUp(vision.getNeededRPM());
                break;
        }

        if (isWarmedUp) {
            shooter.setTrigger(1);
        }
    }

    @Override
    public boolean isFinished() {
        return isWarmedUp;
    }
}