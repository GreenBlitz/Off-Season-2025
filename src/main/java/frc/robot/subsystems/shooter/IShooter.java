package frc.robot.subsystems.shooter;

import edu.wpi.first.math.geometry.Rotation2d;

public interface IShooter {

    void setPower(double power);
    void setVelocity(Rotation2d velocity);
    void setVoltage(double voltage);
    void updateInputs(ShooterInputsAutoLogged inputs);


}
