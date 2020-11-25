package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SetTriggerCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Shooter shooter;
    private double power = 0;

    /**
     * Creates a new SetTrigger command
     * @param shooter a shooter subsystem to be used by this command
     * @param power the speed you want to run the trigger wheel at, negative is outwards
     */
    public SetTriggerCommand(Shooter shooter, double power) {
        this.shooter = shooter;
        this.power = power;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setTrigger(power);
    }

    @Override
    public boolean isFinished() {
        return shooter.getTriggerSpeed() == power;
    }
}