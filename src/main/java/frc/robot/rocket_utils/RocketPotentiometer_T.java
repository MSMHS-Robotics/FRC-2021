package frc.robot.rocket_utils;

/** A simulated potentiometer (specifically for the intake) */
public class RocketPotentiometer_T {
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
     * @return the reading
     */
    public double get() {
        return reading / 5 * fullRange + offset;
    }

    /**
     * Sets the reading, for simulation purposes. Units are in volts from 0-5
     * @param reading the new reading to set the pot to
     */
    public void setReading(double reading) {
        this.reading = reading;
    }
    
}