package frc.robot.subsystems.intake.pivot.simulation;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.simulation.SingleJointedArmSimulation;
import frc.robot.subsystems.intake.pivot.IPivot;
import frc.robot.subsystems.intake.pivot.PivotInputsAutoLogged;

import static frc.robot.subsystems.intake.pivot.simulation.PivotSimulationConstants.PID_CONTROLLER;

public class SimulationPivot implements IPivot {


    private SingleJointedArmSimulation armSimulation;

    public SimulationPivot (){
        this.armSimulation = new SingleJointedArmSimulation(
                PivotSimulationConstants.ARM_SIMULATION
        );

    }

    @Override
    public void setVoltage(double voltage) {
        armSimulation.setInputVoltage(voltage);
    }

    @Override
    public void setPower(double power) {
        armSimulation.setPower(power);
    }

    @Override
    public void setPosition(Rotation2d targetPosition) {
        setVoltage(PID_CONTROLLER.calculate(
                armSimulation.getPosition().getDegrees(),
                targetPosition.getDegrees()
        ));
    }


    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
        inputs.appliedOutput = armSimulation.getVoltage();
        inputs.rotorPosition = armSimulation.getPosition();
        inputs.outputCurrent = armSimulation.getCurrent();

        inputs.encoderPosition = armSimulation.getPosition();
        inputs.isAbsoluteEncoderAlive = true;
    }

}
