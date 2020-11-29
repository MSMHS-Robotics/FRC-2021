package frc.robot.commands.passthrough;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PassThrough;

public class SetPassThroughCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final PassThrough passThrough;
    private double power = 0;

    /**
     * Creates a new SetPassThrough command. Runs the pass through at a given speed
     * @param passThrough a pass-through subsystem to be used by this command
     * @param power the speed you want to run the pass through at (negative is outwards)
     */
    public SetPassThroughCommand(PassThrough passThrough, double power) {
        this.passThrough = passThrough;
        this.power = power;
        addRequirements(passThrough);
    }

    @Override
    public void execute() {
        passThrough.setIdle(false);
        passThrough.setPassThrough(power);
    }

    @Override
    public void end(boolean interrupted) {
        passThrough.setIdle(true);
    }

    @Override
    public boolean isFinished() {
        return passThrough.getSpeed() == power;
    }
}