package frc.robot.rocket_utils;

public interface RocketCANEncoderInterface {
    public void setPosition(double ticks);
    public double getVelocity();
    public void setPositionConversionFactor(double factor);
    public double getPosition();
}