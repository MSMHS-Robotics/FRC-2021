package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Vision extends SubsystemBase {
    private boolean aligned = false;
    private boolean zoom = false;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    public Vision() {
    }

    /** Turns the Limelight LEDs off */
    public void ledsOff() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    /** Turns the Limelight LEDs on */
    public void ledsOn() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }

    /**
     * Returns if we are aligned with the vision target or not
     * @return if we are aligned or not
     */
    public boolean isAligned() {
        return Math.abs(getXOffset()) < Constants.visionConstants.visionThreshold;
    }

    /** Toggles whether we are using 1x or 2x zoom on the Limelight. Changes both the flag and the vision pipeline */
    public void toggleVisionZoom() {
        zoom = !zoom;
        if(zoom) {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        } else {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        }
    }

    /** Changes Limelight to use 2x hardware zoom, aka "snipa" */
    public void zoom() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
        zoom = true;
    }
    
    /** The opposite of zoom(), changes to normal 1x (i.e. none) zoom */
    public void unZoom() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        zoom = false;
    }

    /**
     * Returns if we are zooming or not
     * @return if we are using 2x or 1x hardware zoom on limelight
     */
    public boolean isZoomed() {
        return zoom;
    }

    /**
     * Returns the x-offset from the crosshair to the target.
     * @return the x offset
     */
    public double getXOffset() {  
        ledsOn();
        if (targetsInView()) { // if there are any targets in view
            return table.getEntry("tx").getDouble(0); // x offset
        } else {
            return 0;
        }
    }

    /**
     * Gets whether there are any detected vision targets in view
     * @return whether there are any vision targets in view
     */
    public boolean targetsInView() {
        return table.getEntry("tv").getDouble(0) == 1;
    }

    /**
     * Uses the Limelight to get distance to goal
     * Does this using an equation taken from the limelight docs
     * @return the distance to the target in inches
     */
    private double getDist() {
        unZoom(); // so zoom won't affect results
        ledsOn(); // so we can see

        if (this.targetsInView()) {
            // taken from limelight docs (equation is d = (h2-h1) / tan(a1+a2))
            return 70.25 / Math.tan(10 + table.getEntry("ty").getDouble(0)); 
        } else {
            return -1;
        }
        
        /**
         * the 70.25 is height of center of the circle (in the hexagon frame) in inches minus how high lens is off the groun (20 inches) (h2 - h1)
         * 10 is angle limelight lens is at (a1)
         * ty.getDouble gives the y value of the target from LL's perspective
         * -1 is a default value to return, being unsuccesful
         */ 
    }
    
    /**
     * Uses some arcane magical equation to return the RPM we need based on the distance to the target
     * Found it on SE somewhere (can't remember now where I found it, so...), but not really sure if it's correct
     * 
     * The equation used is:
     *  60 * ( (1/cos(angle) * sqrt(distance^2 / 2 * gravity) ) / (distance * tan(angle) + height))
     * 
     * @return the RPM needed to shoot the PCs to the goal
     */
    public double getNeededRPM() {
        double d = this.getDist(); //distance to target
        double angle = Constants.visionConstants.shooterAngle; //angle we are shooting at
        double g = Constants.visionConstants.accel_due_to_gravity; //acceleration due to gravity
        double h = Constants.visionConstants.limelight_height; //height above ground we are shooting at
        
        // units are in inches and degrees and seconds and stuff
        return 60 * ((1 / Math.cos(angle) * Math.sqrt((0.5 * (d * d) * g) / (d * Math.tan(angle) + h))));
    }
}