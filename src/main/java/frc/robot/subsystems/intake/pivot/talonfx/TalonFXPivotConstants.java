package frc.robot.subsystems.intake.pivot.talonfx;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.utils.devicewrappers.TalonFXWrapper;

public class TalonFXPivotConstants {

    public static final TalonFXConfiguration MOTOR_CONFIGURATION = new TalonFXConfiguration();
    public static final double GEAR_RATIO = 60;

    static {
        MOTOR_CONFIGURATION.Slot0.kP = 1;
        MOTOR_CONFIGURATION.Slot0.kI = 0;
        MOTOR_CONFIGURATION.Slot0.kD = 0;
    }

}
