package frc.robot.subsystems.swerve.modules.simulation;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.simulation.SimpleMotorSimulation;

public class SimulationModuleActions {

    private final SimpleMotorSimulation steerMotor, driveMotor;

    private final PositionVoltage steerPositionRequest = new PositionVoltage(0).withEnableFOC(SimulationModuleConstants.ENABLE_FOC_STEER);
    private final VoltageOut steerVoltageRequest = new VoltageOut(0).withEnableFOC(SimulationModuleConstants.ENABLE_FOC_STEER);

    private final VoltageOut driveVoltageRequest = new VoltageOut(0).withEnableFOC(SimulationModuleConstants.ENABLE_FOC_DRIVE);

    public SimulationModuleActions(SimulationModuleConfigObject simulationModuleConfigObject) {
        this.driveMotor = simulationModuleConfigObject.getDriveMotor();
        this.steerMotor = simulationModuleConfigObject.getSteerMotor();
    }

    public void stop() {
        driveMotor.stop();
        steerMotor.stop();
    }

    public void setTargetDriveVoltage(double voltage) {
        driveMotor.setControl(driveVoltageRequest.withOutput(voltage));
    }

    public void setTargetSteerVoltage(double voltage) {
        steerMotor.setControl(steerVoltageRequest.withOutput(voltage));
    }

    public void setTargetAngle(Rotation2d angle) {
        steerMotor.setControl(steerPositionRequest.withPosition(angle.getRotations()));
    }

}
