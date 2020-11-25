package frc.robot.rocket_utils;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class RocketCANEncoder implements RocketCANEncoderInterface {
    private CANEncoder encoder;

    public RocketCANEncoder(CANSparkMax motor) {
        this.encoder = new CANEncoder(motor);
    }

    @Override
    public void setPosition(double ticks) {
        encoder.setPosition(ticks);
    }

    @Override
    public double getVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void setPositionConversionFactor(double factor) {
        encoder.setPositionConversionFactor(factor);
    }

    @Override
    public double getPosition() {
        return encoder.getPosition();
    }
}