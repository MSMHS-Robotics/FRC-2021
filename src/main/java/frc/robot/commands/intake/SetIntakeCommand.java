package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class SetIntakeCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Intake intake;
    private double power;

    /**
     * Creates a new SetIntakeCommand command
     * sets the intake to a given speed
     * @param intake an intake subsystem to be used by this command
     * @param power the speed you want to run the intake at, negative is reverse
     */
    public SetIntakeCommand(Intake intake, double power) {
        this.intake = intake;
        this.power = power;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntake(power);
    }

    @Override
    public boolean isFinished() {
        return intake.getIntakeSpeed() == power;
    }
}