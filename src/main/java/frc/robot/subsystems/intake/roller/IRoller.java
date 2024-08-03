package frc.robot.subsystems.intake.roller;

public interface IRoller {

    void setPower(double power);
    void setVoltage (double voltage);
    void setVelocity (double velocity);
    void updateInputs (RollerInputsAutoLogged inputs);

}
