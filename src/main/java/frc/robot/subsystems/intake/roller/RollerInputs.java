package frc.robot.subsystems.intake.roller;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class RollerInputs {

    public Rotation2d velocity;
    public double appliedOutput;
    public double outputCurrent;
    public boolean isNoteIn;

}
