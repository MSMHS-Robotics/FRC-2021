package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketCANEncoder;
import frc.robot.rocket_utils.RocketCANEncoderInterface;
import frc.robot.rocket_utils.RocketCANEncoder_T;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketSparkMAX;
import frc.robot.rocket_utils.RocketSparkMAX_T;
import frc.robot.Constants;

/**
 * A subsystem to control the shooter + trigger
 */
public class Shooter extends SubsystemBase {
    private RocketMotor shooterMotor;
    private RocketMotor shooterFollowerMotor;
    private RocketMotor triggerMotor;
    private RocketCANEncoderInterface shooterEncoder;

    private Boolean isIdling = true;
    private PIDController shooterPID;

    private ShuffleboardTab tab = Shuffleboard.getTab("tab");
    private NetworkTableEntry sb_status = tab.add("Status", false).getEntry();
    private NetworkTableEntry sb_rpm = tab.add("Current RPM", 0).getEntry();
    private NetworkTableEntry sb_desiredRPM = tab.add("Target RPM", 0).getEntry();
    private NetworkTableEntry sb_preset_layup = tab.add("Layup RPM", Constants.ShooterPresets.layupRPM).getEntry();
    private NetworkTableEntry sb_preset_line = tab.add("Line RPM", Constants.ShooterPresets.lineRPM).getEntry();
    private NetworkTableEntry sb_preset_trench = tab.add("Trench RPM", Constants.ShooterPresets.trenchRPM).getEntry();
    private NetworkTableEntry sb_preset_max = tab.add("Max RPM", Constants.ShooterPresets.maxRPM).getEntry();
    private NetworkTableEntry debugButton = tab.add("Debug Mode?", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    

    /**
     * Creates a new Shooter
     */
    public Shooter() {
        if (Constants.unitTests) {
            shooterMotor = new RocketSparkMAX_T(Constants.shooter1_p);
            shooterFollowerMotor = new RocketSparkMAX_T(Constants.shooter2_p);
            triggerMotor = new RocketSparkMAX_T(Constants.trigger_p);

            shooterEncoder = new RocketCANEncoder_T((RocketSparkMAX_T) shooterMotor);
        } else {
            shooterMotor = new RocketSparkMAX(Constants.shooter1_p);
            shooterFollowerMotor = new RocketSparkMAX(Constants.shooter2_p);
            triggerMotor = new RocketSparkMAX(Constants.trigger_p);

            shooterEncoder = new RocketCANEncoder((CANSparkMax) shooterMotor);
        }

        shooterPID = new PIDController(Constants.shooterPID.kP, Constants.shooterPID.kI, Constants.shooterPID.kD, Constants.shooterPID.kFF);
        shooterFollowerMotor.setInverted(true);
    }

    /**
     * Runs the trigger motor at a given speed
     * @param power the speed at which to run the motor
     */
    public void setTrigger(double power) {
        triggerMotor.set(power);
    }

    /**
     * A method to get the trigger motor's speed
     * @return the last set speed of the trigger motor
     */
    public double getTriggerSpeed() {
        return triggerMotor.get();
    }

    /**
     * Sets the state of the shooter (idling or not)
     * @param state the state you want the shooter set to
     */
    public void setIdle(boolean state) {
        isIdling = state;
    }

    /**
     * Resets the shooter encoders and control-y bits
     * Also sets all motors to 0
     */
    public void reset() {
        shooterEncoder.setPosition(0);
        shooterPID.reset();
        shooterMotor.set(0);
        shooterFollowerMotor.set(0);
        triggerMotor.set(0);
    }

    /**
     * Warms up the shooter motors to the desired RPM
     * @param RPM the speed (in rotations per minute) you want to shoot at
     * @return whether or not we are at that RPM
     */
    public boolean warmUp(double RPM) {
        shooterMotor.set(shooterPID.calculate(shooterEncoder.getVelocity(), RPM));
        shooterFollowerMotor.set(shooterPID.calculate(shooterEncoder.getVelocity(), RPM));
        return shooterPID.atSetpoint();
    }

    /**
     * the required isGood() method
     * Checks all motors to see if they exist
     * @return if the subsystem is functioning ("good") or not
     */
    public boolean isGood() {
        return shooterMotor.isMotorNotNull() && shooterFollowerMotor.isMotorNotNull() && triggerMotor.isMotorNotNull();
    }

    @Override
    public void periodic() {
        if (isIdling) {
            setTrigger(-0.5);
        }

        sb_status.setBoolean(this.isGood());
        sb_rpm.setDouble(this.shooterEncoder.getVelocity());
        sb_desiredRPM.setDouble(this.shooterPID.getSetpoint());
    }
}