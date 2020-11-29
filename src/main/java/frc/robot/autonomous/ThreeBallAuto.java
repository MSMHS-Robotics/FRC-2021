package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveDistanceCommand;
import frc.robot.commands.drivetrain.VisionAlignCommand;
import frc.robot.commands.intake.LowerIntakeCommand;
import frc.robot.commands.shooter.ShootAutoCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class ThreeBallAuto extends SequentialCommandGroup {
    public ThreeBallAuto(Drivetrain drivetrain, Intake intake, Shooter shooter, Vision vision) {
        super(
            new LowerIntakeCommand(intake),
            new VisionAlignCommand(drivetrain, vision), // don't need this if you don't want it if something is jank
            new ShootAutoCommand(shooter, vision, -1), // change the -1 to a preset if you don't want to use Limelight sensing
            // need a waitCommand or something. Add a timeoutShootCommand or something
            new DriveDistanceCommand(drivetrain, -200) // drive off the line for the RP
        );
    }
}