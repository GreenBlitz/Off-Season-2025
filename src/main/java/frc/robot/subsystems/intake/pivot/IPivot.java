package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public interface IPivot {

    void setVoltage (double voltage);
    void setPower (double power);
    void setPosition (Rotation2d targetPosition);
    void updateInputs (PivotInputsAutoLogged inputs);

}
