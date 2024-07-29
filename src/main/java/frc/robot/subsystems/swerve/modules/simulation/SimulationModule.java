package frc.robot.subsystems.swerve.modules.simulation;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.swerve.modules.IModule;
import frc.robot.subsystems.swerve.modules.ModuleConstants;
import frc.robot.subsystems.swerve.modules.ModuleUtils;
import frc.robot.subsystems.swerve.modules.inputs.ModuleInputsContainer;
import frc.utils.Conversions;
import org.littletonrobotics.junction.Logger;

public class SimulationModule implements IModule {

    private final ModuleUtils.ModuleName moduleName;
    private final SimulationModuleActions simulationModuleActions;
    private final SimulationModuleStatus simulationModuleStatus;

    public SimulationModule(ModuleUtils.ModuleName moduleName) {
        this.moduleName = moduleName;

        SimulationModuleConfigObject simulationModuleConfigObject = new SimulationModuleConfigObject();
        this.simulationModuleActions = new SimulationModuleActions(simulationModuleConfigObject);
        this.simulationModuleStatus = new SimulationModuleStatus(simulationModuleConfigObject);
    }


    private double toDriveMeters(Rotation2d angle) {
        return Conversions.angleToDistance(angle, SimulationModuleConstants.WHEEL_DIAMETER_METERS);
    }


    @Override
    public void stop() {
        simulationModuleActions.stop();
    }

    @Override
    public void setBrake(boolean brake) {
        Logger.recordOutput(ModuleUtils.getLoggingPath(moduleName) + "driveMotorVoltage", simulationModuleStatus.getDriveVoltage());
    }

    @Override
    public void resetByEncoder() {
        Logger.recordOutput(ModuleUtils.getLoggingPath(moduleName) + "tried to reset by encoder");
    }


    @Override
    public void runSteerMotorByVoltage(double voltage) {
        simulationModuleActions.setTargetSteerVoltage(voltage);
    }

    @Override
    public void runDriveMotorByVoltage(double voltage) {
        simulationModuleActions.setTargetDriveVoltage(voltage);
    }


    @Override
    public void setTargetOpenLoopVelocity(double targetVelocityMetersPerSecond) {
        double voltage = ModuleUtils.velocityToOpenLoopVoltage(
                targetVelocityMetersPerSecond,
                simulationModuleStatus.getSteerVelocity(),
                0,
                SimulationModuleConstants.MAX_SPEED_PER_SECOND,
                SimulationModuleConstants.WHEEL_DIAMETER_METERS,
                ModuleConstants.VOLTAGE_COMPENSATION_SATURATION
        );
        Logger.recordOutput(ModuleUtils.getLoggingPath(moduleName) + "driveMotorVoltage", simulationModuleStatus.getDriveVoltage());
        runDriveMotorByVoltage(voltage);
    }

    @Override
    public void setTargetClosedLoopVelocity(double targetVelocityMetersPerSecond) {
        setTargetOpenLoopVelocity(targetVelocityMetersPerSecond);
    }

    @Override
    public void setTargetAngle(Rotation2d angle) {
        simulationModuleActions.setTargetAngle(angle);
    }


    @Override
    public void updateInputs(ModuleInputsContainer inputs) {
        inputs.getSteerMotorInputs().angle = simulationModuleStatus.getSteerPosition();
        inputs.getSteerMotorInputs().velocity = simulationModuleStatus.getSteerVelocity();
        inputs.getSteerMotorInputs().voltage = simulationModuleStatus.getSteerVoltage();
        inputs.getSteerMotorInputs().angleOdometrySamples = new Rotation2d[]{inputs.getSteerMotorInputs().angle};

        inputs.getDriveMotorInputs().angle = simulationModuleStatus.getDrivePositionAngle();
        inputs.getDriveMotorInputs().velocity = simulationModuleStatus.getDriveVelocityAnglePerSecond();
        inputs.getDriveMotorInputs().current = simulationModuleStatus.getDriveCurrent();
        inputs.getDriveMotorInputs().voltage = simulationModuleStatus.getDriveVoltage();
        inputs.getDriveMotorInputs().distanceMeters = toDriveMeters(inputs.getDriveMotorInputs().angle);
        inputs.getDriveMotorInputs().velocityMeters = toDriveMeters(inputs.getDriveMotorInputs().velocity);
        inputs.getDriveMotorInputs().distanceMetersOdometrySamples = new double[]{inputs.getDriveMotorInputs().distanceMeters};
    }

}
