/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.TreeMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.DriveOffLineAuto;
import frc.robot.autonomous.ThreeBallAuto;
import frc.robot.commands.climber.ClimbCommand;
import frc.robot.commands.climber.ExtendClimberCommand;
import frc.robot.commands.intake.LowerIntakeCommand;
import frc.robot.commands.intake.RaiseIntakeCommand;
import frc.robot.commands.intake.SetIntakeCommand;
import frc.robot.commands.passthrough.SetPassThroughCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.shooter.WarmUpCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PassThrough;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /** Joysticks */
    private final Joystick gamepad1 = new Joystick(0);
    private final Joystick gamepad2 = new Joystick(1);

    private JoystickButton aButton = new JoystickButton(gamepad1, 1);
    private JoystickButton bButton = new JoystickButton(gamepad1, 2);
    private JoystickButton xButton = new JoystickButton(gamepad1, 3);
    private JoystickButton yButton = new JoystickButton(gamepad1, 4);

    private JoystickButton aButton2 = new JoystickButton(gamepad2, 1);
    private JoystickButton bButton2 = new JoystickButton(gamepad2, 2);
    private JoystickButton xButton2 = new JoystickButton(gamepad2, 3);
    private JoystickButton yButton2 = new JoystickButton(gamepad2, 4);
    
    private JoystickButton leftBumper = new JoystickButton(gamepad1, 5);
    private JoystickButton rightBumper = new JoystickButton(gamepad1, 6);
    private JoystickButton leftBumper2 = new JoystickButton(gamepad2, 5);
    private JoystickButton rightBumper2 = new JoystickButton(gamepad2, 6);
  
    private JoystickButton back = new JoystickButton(gamepad1, 7);
    private JoystickButton back2 = new JoystickButton(gamepad2, 7);
    
    private JoystickButton start = new JoystickButton(gamepad1, 8);
    private JoystickButton start2 = new JoystickButton(gamepad2, 8);

    /** Subsystsm */
    private final Climber climber = new Climber(false);
    private final Drivetrain drivetrain = new Drivetrain(false);
    private final Intake intake = new Intake(false);
    private final PassThrough passThrough = new PassThrough(false);
    private final Shooter shooter = new Shooter(false);
    private final Vision vision = new Vision();
    
    /** 
     * Commands
     */
    /** Intake */
    private final RaiseIntakeCommand raiseIntake = new RaiseIntakeCommand(intake);
    private final LowerIntakeCommand lowerIntake = new LowerIntakeCommand(intake);
    private final SetIntakeCommand intakeIn = new SetIntakeCommand(intake, 1);
    private final SetIntakeCommand intakeStop = new SetIntakeCommand(intake, 0);
    private final SetIntakeCommand intakeOut = new SetIntakeCommand(intake, -1);

    /** Pass-through */
    private final SetPassThroughCommand indexIn = new SetPassThroughCommand(passThrough, 1);

    /** Shooter */
    private final ShootCommand shoot = new ShootCommand(shooter, vision, gamepad1);
    private final WarmUpCommand warmUp2 = new WarmUpCommand(shooter, vision, gamepad2);

    /** Climber */
    private final ExtendClimberCommand extendClimber = new ExtendClimberCommand(climber);
    private final ClimbCommand climb = new ClimbCommand(climber);

    // The same janky joystick stuff from last year
    private final RunCommand runDrivetrain = new RunCommand(() -> drivetrain.drive(gamepad1.getRawAxis(1), gamepad1.getRawAxis(5)), drivetrain);

    //auto selector stuff (copied + pasted from last year's code)
    private TreeMap<String, Command> autos = new TreeMap<String, Command>();
    private ArrayList<String> autoNames;
    private int curr_auto = 0;
    private int lengthOfList;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        autos.put("Drive Off Line", new DriveOffLineAuto(drivetrain));
        autos.put("3 Ball Auto", new ThreeBallAuto(drivetrain, intake, shooter, vision));
        autoNames = new ArrayList<>(autos.keySet());
        lengthOfList = autoNames.size();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        rightBumper.whenPressed(lowerIntake);
        rightBumper.whenPressed(intakeIn);
        rightBumper.whenReleased(intakeStop);
        rightBumper.whileHeld(indexIn);
        yButton.whenPressed(raiseIntake);

        bButton.whileHeld(shoot);
        bButton2.whileHeld(warmUp2);

        xButton2.whenPressed(extendClimber);
        yButton2.whenPressed(climb);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @param index the index (starting at 0) of the command you want to run in auto from the list of autos
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand(int index) {
       return autos.get(autoNames.get(index));
    }

    public String getAutoName(int index) {
        return autoNames.get(index);
    }

    /** Need this for the auto chooser */
    public Joystick getJoystick1() {
        return gamepad1;
    }
}
