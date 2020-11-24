package frc.robot.rocket_utils;

import edu.wpi.first.wpilibj.DigitalInput;

public class RocketDigitalInput_T extends DigitalInput {
    public int port;
    public boolean state = false;

    public RocketDigitalInput_T(int port) {
        super(port);
        this.port = port;
    }

    @Override
    public boolean get() {
        return state;
    }

    
}