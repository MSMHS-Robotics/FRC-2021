package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

/** A command to shoot */
public class ShootAutoCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Shooter shooter;
    private final Vision vision;
    private boolean isWarmedUp = false;
    private double rpm;

    /**
     * Creates a new ShootAutoCommand command. Warms up the shooter to the given rpm and then shoots.
     * Ends immediately afterwards though...
     * 
     * @param shooter a shooter subsystem to be used by this command
     * @param vision a vision subsystem to be used by this command (for the distance to the goal)
     * @param rpm the speed you want to shoot at. -1 to shoot based off of what the limelight says
     */
    public ShootAutoCommand(Shooter shooter, Vision vision, double rpm) {
        this.shooter = shooter;
        this.vision = vision;
        this.rpm = rpm;
        addRequirements(shooter, vision);
    }

    @Override
    public void execute() {
        if (rpm < 0) {
            isWarmedUp = shooter.warmUp(vision.getNeededRPM());
        } else {
            isWarmedUp = shooter.warmUp(rpm);
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