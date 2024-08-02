package frc.robot.subsystems.shooter;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class ShooterInputs {
    public Rotation2d upperShooterVelocity;
    public double upperShooterOutput;
    public double upperShooterCurrent;

    public Rotation2d lowerShooterVelocity;
    public double lowerShooterOutput;
    public double lowerShooterCurrent;
    public boolean isNodeIn;



}
