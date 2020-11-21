package frc.robot.rocket_utils;

public class RocketEncoder_T {
    private int port1, port2;
    private double ticks = 0;
    
    public RocketEncoder_T(int port1, int port2) {
        this.port1 = port1;
        this.port2 = port2;
    }

    public void reset() {
        this.ticks = 0;
    }

    public void get() {
        return ticks;
    }
}