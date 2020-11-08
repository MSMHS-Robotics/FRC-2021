package frc.robot.rocket_utils;

public class RocketTalon {
    /** private stuff? */
    private Boolean unitTests;
    private WPI_TalonSRX motor;

    /** Public so we can read while testing */
    public int power;

    /** Creates a new RocketTalon
     *  If unitTests is false then this behaves _exactly_ like a normal Talon
     *  Otherwise it just implements something that looks like a Talon but doesn't actaully do anything.
     *  This allows us to run it in the virtual
     */
    public RocketTalon(int port, boolean unitTests) {
        motor = new WPI_TalonSRX(port);
    }

    @Override
    public void set(int power) {
        if (unitTests) {
            this.power = power;
        }
        else {
            motor.set(power)
        }
    }
}