package frc.robot.commands.passthrough;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PassThrough;

public class PassThroughPitTestCommand extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final PassThrough passThrough;
    private Boolean testsDone = false;

    /**
     * Creates a new PassThroughPitTestCommand command. Tests the pass-through in the pits/on a raised bed where it can't murder anyone
     * 
     * @param passThrough a pass-through subsystem to be used by this command
     */
    public PassThroughPitTestCommand(PassThrough passThrough) {
        this.passThrough = passThrough;
        addRequirements(passThrough);
    }

    @Override
    public void execute() {
        passThrough.setProgressBar(0);
        passThrough.setPassThrough(0);

        if (passThrough.isGood()) {
            passThrough.setProgressBar(0.33);

            passThrough.setPassThrough(1);
            if (passThrough.getSpeed() == 1) {
                passThrough.setProgressBar(0.66);

                passThrough.setPassThrough(-1);
                if (passThrough.getSpeed() == -1) {
                    passThrough.setProgressBar(1);
                } else {
                    passThrough.setTestStatus("Failed on: setting to speed -1");
                }
            } else {
                passThrough.setTestStatus("Failed on: setting speed to 1");
            }
        } else {
            passThrough.setTestStatus("Failed on: isGood() check");
        }
    }

    @Override
    public void end(boolean interrupted) {
        passThrough.setPassThrough(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}