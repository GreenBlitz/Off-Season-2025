package frc.robot.subsystems.intake.pivot.simulation;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class PivotSimulationConstants {


    protected static DCMotor MOTOR = DCMotor.getKrakenX60Foc(1);
    protected static double GEARING = 60;
    protected static double MASS = 2;
    protected static double LENGTH = 0.4;

    protected static SingleJointedArmSim ARM_SIMULATION = new SingleJointedArmSim(
            MOTOR,
            GEARING,
            SingleJointedArmSim.estimateMOI(LENGTH,MASS),
            LENGTH,
            0,
            Math.toRadians(90),
            false,
            Math.toRadians(90)
    );

    protected static PIDController PID_CONTROLLER = new PIDController(1,0,0);


}
