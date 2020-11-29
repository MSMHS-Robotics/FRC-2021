package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakePitTestCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Intake intake;
    private Boolean testsDone = false;

    /**
     * Creates a new IntakePitTestCommand command. Tests the intake in
     * the pits/on a raised bed where it can't murder anyone
     * 
     * @param intake an intake subsystem to be used by this command
     */
    public IntakePitTestCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setProgressBar(0);
        intake.setIntake(0);

        if (intake.isGood()) {
            intake.setProgressBar(0.2);

            intake.setIntake(1);
            if (intake.getIntakeSpeed() == 1) {
                intake.setProgressBar(0.4);

                intake.setIntake(-1);
                if (intake.getIntakeSpeed() == -1) {
                    intake.setProgressBar(0.6);

                    intake.raiseIntake();
                    //wait here
                    if (intake.isIntakeRaised()) {
                        intake.setProgressBar(0.8);

                        intake.lowerIntake();
                        // wait here
                        if (!intake.isIntakeRaised()) {
                            intake.setProgressBar(1);
                        } else {
                            intake.setTestStatus("Failed on: lowering intake");
                        }
                    } else {
                        intake.setTestStatus("Failed on: raising intake");
                    }
                } else {
                    intake.setTestStatus("Failed on: setting speed to -1");
                }
            } else {
                intake.setTestStatus("Failed on: setting speed to 1");
            }
        } else {
            intake.setTestStatus("Failed on: isGood() check");
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntake(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}