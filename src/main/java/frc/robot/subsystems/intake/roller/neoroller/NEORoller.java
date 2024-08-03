package frc.robot.subsystems.intake.roller.neoroller;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import au.grapplerobotics.LaserCan;
import au.grapplerobotics.ConfigurationFailedException;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.constants.IDs;
import frc.robot.subsystems.intake.roller.IRoller;
import frc.robot.subsystems.intake.roller.RollerInputsAutoLogged;

public class NEORoller implements IRoller {

    private CANSparkMax motor;
    private LaserCan laserCan;

    public NEORoller () {
        this.motor = new CANSparkMax(IDs.CANSparkMAXIDs.INTAKE_ROLLER_ID, CANSparkLowLevel.MotorType.kBrushless);
        this.laserCan = new LaserCan(IDs.LaseCANIDs.INTAKE_LASERCAN_ID);

        try {
            laserCan.setRangingMode(LaserCan.RangingMode.SHORT);
            laserCan.setRegionOfInterest(new LaserCan.RegionOfInterest(8, 8, 16, 16));
            laserCan.setTimingBudget(LaserCan.TimingBudget.TIMING_BUDGET_33MS);
        } catch (ConfigurationFailedException e) {
            System.out.println("Configuration failed! " + e);
        }
    }
    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void setVelocity(Rotation2d velocity) {
        motor.getPIDController().setReference(velocity.getRotations(), CANSparkBase.ControlType.kVelocity);
    }

    @Override
    public void updateInputs(RollerInputsAutoLogged inputs) {

        inputs.velocity = Rotation2d.fromRotations(motor.getEncoder().getVelocity());
        inputs.appliedOutput = motor.getAppliedOutput();
        inputs.outputCurrent = motor.getOutputCurrent();

    }

}
