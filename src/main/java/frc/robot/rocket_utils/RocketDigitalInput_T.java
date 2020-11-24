package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.DigitalInput;

/** A class to simulate a digital input */
public class RocketDigitalInput_T extends DigitalInput {
    public int port;
    public boolean state = false;

    /**
     * Creates a new RocketDigitalInput_T
     * @param port the channel the input is attached to
     */
    public RocketDigitalInput_T(int port) {
        super(port);
        this.port = port;
    }

    /**
     * Gets the state of the digital input
     * @return the state of the digital input
     */
    @Override
    public boolean get() {
        return state;
    }

    /**
     * Gets the channel/port that was passed to the constructor
     * Check this against the other ports to make sure there are no conflicts in testing
     * @return the channel of this input
     */
    @Override
    public int getChannel() {
        return port;
    }

    /**
     * Sets the state of the input, used for simulation purposes
     * @param state the state of the input
     */
    public void set(boolean state) {
        this.state = state;
    }

    
}