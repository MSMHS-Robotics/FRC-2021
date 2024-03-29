package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketDigitalInput_T;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.rocket_utils.RocketTalon_T;
import frc.robot.Constants;

/**
 * A subsystem to control the climber
 */
public class Climber extends SubsystemBase {
    private RocketMotor climberMotor;
    private RocketMotor extendMotor;
    private DigitalInput limitSwitch;
    private Boolean extendClimber = false;

    private ShuffleboardTab tab = Shuffleboard.getTab("tab");
    private NetworkTableEntry sb_status = tab.add("Status", false).getEntry();
    private NetworkTableEntry sb_extended = tab.add("Climber Extended", false).getEntry();
    //private NetworkTableEntry sb_current = tab.add("Current Draw", 0).getEntry(); // not sure how to implement this, might need the PDP class back
    private NetworkTableEntry sb_climberSpeed = tab.add("Climb Motor Speed", 0).getEntry();
    private NetworkTableEntry sb_extendSpeed = tab.add("Extend Motor Speed", 0).getEntry();
    private NetworkTableEntry debugButton = tab.add("Debug Mode?", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
  

    public Climber(boolean unitTests) {
        if (unitTests) {
            climberMotor = new RocketTalon_T(Constants.climber_p);
            extendMotor = new RocketTalon_T(Constants.climberExtend_p);
            limitSwitch = new RocketDigitalInput_T(Constants.limitSwitch_p);
        } else {
            climberMotor = new RocketTalon(Constants.climber_p);
            extendMotor = new RocketTalon(Constants.climberExtend_p);
            limitSwitch = new DigitalInput(Constants.limitSwitch_p);
        }
    }

    /**
     * Sets the climber to a given speed
     * 
     * @param power the speed at which you want the climber to run (negative means reverse)
     */
    public void setClimber(double power) {
        climberMotor.set(power); // != null is handled inside RocketUtils
    }

    /**
     * Extends the climber
     */
    public void extendClimber() {
        extendClimber = true;
    }

    /**
     * Tells if the climber is extended or not
     * @return a boolean representing if the climber is extended or not
     */
    public Boolean isClimberExtended() {
        return limitSwitch.get();
    }

    /**
     * Resets the climber subsystem to its pre-start state
     */
    public void reset() {
        climberMotor.set(0);
        extendMotor.set(0);
    }

    /**
     * the required isGood() method
     * 
     * @return a Boolean representing if the motors can be reached or not
     */
    public Boolean isGood() {
        return climberMotor.isMotorNotNull() && extendMotor.isMotorNotNull(); // RocketUtils at work
    }

    @Override
    public void periodic() {
        if(extendClimber) { // if we need to extend the climber
            extendMotor.set(1); // extend it until
            if(limitSwitch.get()) { // we reach the top
                extendMotor.set(0); // and then stop it. Should lead to minor oscillating at the top
            }
        }

        /**
         * Our shuffleboard values
         */
        sb_status.setBoolean(this.isGood());
        sb_extended.setBoolean(this.isClimberExtended());
        if (debugButton.getBoolean(false)) { // if we're in debug mode
            sb_climberSpeed.setDouble(climberMotor.get()); // push extra information
            sb_extendSpeed.setDouble(extendMotor.get());
        }
    }
}
