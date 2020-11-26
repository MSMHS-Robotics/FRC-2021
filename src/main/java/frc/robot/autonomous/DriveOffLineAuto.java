package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveDistanceCommand;
import frc.robot.subsystems.Drivetrain;

public class DriveOffLineAuto extends SequentialCommandGroup {
    public DriveOffLineAuto(Drivetrain drivetrain) {
        super(
            new DriveDistanceCommand(drivetrain, 200)
        );
    }
}