package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.rocket_utils.RocketMotor;
import frc.robot.rocket_utils.RocketTalon;
import frc.robot.rocket_utils.RocketTalon_T;
import frc.robot.Constants;

/**
 * A subsystem to control the indexer/pass-through
 */
public class PassThrough extends SubsystemBase {
    private RocketMotor passThroughMotor;
    private Boolean isIdling = true;

    private ShuffleboardTab tab = Shuffleboard.getTab("tab");
    private NetworkTableEntry sb_status = tab.add("Status", false).getEntry();
    private NetworkTableEntry sb_speed = tab.add("PassThrough Speed", 0).getEntry();
    private NetworkTableEntry sb_resetSubsystem = tab.add("Reset PassThrough", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    private NetworkTableEntry sb_resetAll = tab.add("Hard-Reset Everything", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    private NetworkTableEntry debugButton = tab.add("Debug Mode?", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    
  
    /**
     * Creates a new PassThrough.
     */
    public PassThrough(boolean unitTests) {
        if (unitTests) {
            passThroughMotor = new RocketTalon_T(Constants.passThrough_p);
        } else {
            passThroughMotor = new RocketTalon(Constants.passThrough_p);
        }
    }

    /**
     * Sets the pass-through to a given speed
     * @param power the speed at which you want the pass-through to run (negative means reverse)
    */
    public void setPassThrough(double power) {
        passThroughMotor.set(power); // != null is handled inside RocketUtils
    }

    /**
     * Gets the speed of the pass through
     * @return the last set speed of the pass through
     */
    public double getSpeed() {
        return passThroughMotor.get();
    }

    /**
     * Sets the state of the pass-through (idling or not)
     * @param state the state you want the pass-through set to
     */
    public void setIdle(boolean state) {
        isIdling = state;
    }

    /**
     * Resets the pass-through subsystem to its pre-start state
     */
    public void reset() {
        passThroughMotor.set(0);
    }

    /**
     * the required isGood() method
     * here we only have one motor so it's pretty easy
     * @return a Boolean representing if the motor can be reached or not
     */
    public Boolean isGood() {
        return passThroughMotor.isMotorNotNull(); // RocketUtils at work
    }

    @Override
    public void periodic() {
        if (isIdling) { // if we're idling
            setPassThrough(0.5); // move in
        }
        
        /**
         * Our shuffleboard stuff
         */
        sb_status.setBoolean(this.isGood());
        if (debugButton.getBoolean(false)) {
            sb_speed.setDouble(this.passThroughMotor.get());
        }

        /**
         * The subsystem reset button
         */
        if (sb_resetSubsystem.getBoolean(false)) { // default value of false
            reset(); // reset
            sb_resetSubsystem.setBoolean(false); // turn button back off
        }

        /**
         * The reset-all all button
         */
        if (sb_resetAll.getBoolean(false)) {
            reset();
            sb_resetAll.setBoolean(false);
        }
    }
}
