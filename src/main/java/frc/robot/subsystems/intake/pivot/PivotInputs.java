package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class PivotInputs {

    public Rotation2d rotorPosition;
    public Rotation2d encoderPosition;
    public double appliedOutput;
    public double outputCurrent;
    public boolean isAbsoluteEncoderAlive;



}
