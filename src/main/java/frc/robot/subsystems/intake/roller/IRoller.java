package frc.robot.subsystems.intake.roller;

import edu.wpi.first.math.geometry.Rotation2d;

public interface IRoller {

    void setPower(double power);
    void setVoltage (double voltage);
    void setVelocity (Rotation2d velocity);
    void updateInputs (RollerInputsAutoLogged inputs);

}
