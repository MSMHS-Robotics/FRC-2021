package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SetIdleShooterCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Shooter shooter;

    /**
     * Creates a new SetShooterIdle command
     * @param shooter an intake subsystem to be used by this command
     */
    public SetIdleShooterCommand(Shooter shooter) {
        this.shooter = shooter
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setIdle();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}