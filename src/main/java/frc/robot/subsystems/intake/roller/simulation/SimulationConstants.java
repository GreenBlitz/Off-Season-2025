package frc.robot.subsystems.intake.roller.simulation;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.BooleanSupplier;

public class SimulationConstants {


    protected static DCMotor MOTOR_TYPE = DCMotor.getNEO(1);
    protected static double GEARING = 10;
    protected static double MOMENT_OF_INERTIA = 0.004;
    protected static PIDController PID_CONTROLLER = new PIDController(1,0,0);
    protected static BooleanSupplier IS_NOTE_IN_SUPPLIER = () -> SmartDashboard.getBoolean("intake note in supplier", false);
}
