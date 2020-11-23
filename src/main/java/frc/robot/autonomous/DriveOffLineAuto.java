package frc.robot.autonomous;

public class DriveOffLineAuto extends SequentialCommandGroup {
    public DriveOffLineAuto(Drivetrain drivetrain) {
        super(
            new DriveDistanceCommand(drivetrain, 200)
        );
    }
}