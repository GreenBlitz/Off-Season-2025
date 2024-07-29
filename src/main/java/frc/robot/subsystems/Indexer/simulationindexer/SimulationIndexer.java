package frc.robot.subsystems.Indexer.simulationindexer;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Indexer.IIndexer;
import frc.robot.subsystems.Indexer.IndexerInputsAutoLogged;
import frc.utils.battery.BatteryUtils;

public class SimulationIndexer implements IIndexer {

    private DCMotorSim motor;
    private double inputVoltage;
    private PIDController pidController;

    public SimulationIndexer(){
        this.motor = SimulationConstants.getMotorSimulation();
        this.inputVoltage = 0;
        this.pidController = SimulationConstants.getPIDController();
    }


    @Override
    public void setPower(double power) {
        setVoltage(MathUtil.clamp(
                power * BatteryUtils.DEFAULT_VOLTAGE,
                -BatteryUtils.DEFAULT_VOLTAGE,
                BatteryUtils.DEFAULT_VOLTAGE
        ));
    }

    @Override
    public void setVoltage(double voltage) {
        inputVoltage = MathUtil.clamp(
               voltage,
                -BatteryUtils.DEFAULT_VOLTAGE,
                BatteryUtils.DEFAULT_VOLTAGE
        );
        motor.setInputVoltage(inputVoltage);
    }

    @Override
    public void setVelocity(double velocity) {
        setVoltage(pidController.calculate(motor.getAngularVelocityRadPerSec(), velocity));
    }

    @Override
    public void setBrake() {

    }

    @Override
    public void setCoast() {

    }

    @Override
    public void updateInputs(IndexerInputsAutoLogged inputs) {
        inputs.velocity = Rotation2d.fromRadians(motor.getAngularVelocityRadPerSec()).getRotations();
        inputs.appliedOutput = inputVoltage;
        inputs.outputCurrent = motor.getCurrentDrawAmps();
        inputs.ampSwitch = SmartDashboard.getBoolean("Amper Limit Switch", false);;
        inputs.shooterSwitch = SmartDashboard.getBoolean("Shooter Limit Switch", false);;
    }

}
