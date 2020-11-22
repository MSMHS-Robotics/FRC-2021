package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/** A simulated potentiometer (specifically for the intake) */
public class RocketPotentiometer_T implements Potentiometer {
    public int channel;
    public double fullRange;
    public double offset;
    public double reading = 0;

    /** Creates a new RocketPotentiometer_T */
    public RocketPotentiometer_T(int channel, double fullRange, double offset) {
        this.channel = channel;
        this.fullRange = fullRange;
        this.offset = offset;
    }

    /**
     * Gets the current reading scaled by fullrange and added to offset
     * 
     * @return the reading
     */
    public double get() {
        return reading / 5 * fullRange + offset;
    }

    /**
     * Sets the reading, for simulation purposes. Units are in volts from 0-5
     * 
     * @param reading the new reading to set the pot to
     */
    public void setReading(double reading) {
        this.reading = reading;
    }

    /** Ignore these, these are here for no errors */
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {return null;}

    @Override
    public double pidGet() {return 0;}
    
}