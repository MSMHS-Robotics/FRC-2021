package frc.robot.rocket_utils;

public class RocketCANEncoder_T {
    private RocketSparkMAX_T motor;
    private double ticks = 0;

    public RocketCANEncoder_T(RocketSparkMAX_T motor) {
        this.motor = motor;
    }

    public void reset() {
        this.ticks = 0;
    }
}