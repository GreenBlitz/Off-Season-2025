package frc.robot.subsystems.intake.roller.simulation;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.subsystems.intake.roller.IRoller;
import frc.robot.subsystems.intake.roller.RollerInputsAutoLogged;
import frc.utils.battery.BatteryUtils;

import java.util.function.BooleanSupplier;

import static frc.robot.subsystems.intake.roller.simulation.SimulationConstants.GEARING;
import static frc.robot.subsystems.intake.roller.simulation.SimulationConstants.IS_NOTE_IN_SUPPLIER;
import static frc.robot.subsystems.intake.roller.simulation.SimulationConstants.MOMENT_OF_INERTIA;
import static frc.robot.subsystems.intake.roller.simulation.SimulationConstants.MOTOR_TYPE;
import static frc.robot.subsystems.intake.roller.simulation.SimulationConstants.PID_CONTROLLER;

public class SimulationRoller implements IRoller {


    private DCMotorSim motor;

    private double inputVoltage;
    BooleanSupplier isNoteInSupplier;
    public SimulationRoller() {

        this.motor = new DCMotorSim(MOTOR_TYPE, GEARING, MOMENT_OF_INERTIA);
        this.isNoteInSupplier = IS_NOTE_IN_SUPPLIER;
    }


    @Override
    public void setPower(double power) {
        setVoltage(BatteryUtils.DEFAULT_VOLTAGE * power);
    }

    @Override
    public void setVoltage(double voltage) {
        this.inputVoltage = voltage;
        motor.setInputVoltage(
                MathUtil.clamp(
                        voltage,
                        BatteryUtils.DEFAULT_VOLTAGE,
                        -BatteryUtils.DEFAULT_VOLTAGE
                )
        );
    }

    @Override
    public void setVelocity(Rotation2d velocity) {
        setVoltage(
                PID_CONTROLLER.calculate(
                        motor.getAngularVelocityRPM(),
                        velocity.getRadians()
                )
        );
    }

    @Override
    public void updateInputs(RollerInputsAutoLogged inputs) {
        inputs.velocity = Rotation2d.fromRotations(motor.getAngularVelocityRPM());
        inputs.appliedOutput = inputVoltage;
        inputs.outputCurrent = motor.getCurrentDrawAmps();
        inputs.isNoteIn = isNoteInSupplier.getAsBoolean();
    }

}
