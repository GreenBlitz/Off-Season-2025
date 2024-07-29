package frc.robot.subsystems.Indexer.simulationindexer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class SimulationConstants {

    private static final DCMotor MOTOR_TYPE = DCMotor.getFalcon500Foc(1);
    private static final double GEARING = 100;
    private static final double MOMENT_OF_INERTIA = 0.025;

    private static final double K_P = 1;
    private static final double K_I = 0;
    private static final double K_D = 0;

    public static PIDController getPIDController (){
        return new PIDController(K_P,K_I,K_D);
    }

    public static DCMotorSim getMotorSimulation (){
        return new DCMotorSim(
                MOTOR_TYPE,
                GEARING,
                MOMENT_OF_INERTIA
        );
    }


}
