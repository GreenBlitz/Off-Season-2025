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
import frc.utils.lasercan.LaserCANWrapper;

import static frc.robot.subsystems.intake.roller.neoroller.NEORollerConstants.INTAKE_WIDTH;
import static frc.robot.subsystems.intake.roller.neoroller.NEORollerConstants.LASER_CAN_REGION_OF_INTEREST;
import static frc.robot.subsystems.intake.roller.neoroller.NEORollerConstants.RANGE_MODE;
import static frc.robot.subsystems.intake.roller.neoroller.NEORollerConstants.TIMING_BUDGET;

public class NEORoller implements IRoller {

    private CANSparkMax motor;
    private LaserCANWrapper laserCan;

    public NEORoller () {
        this.motor = new CANSparkMax(IDs.CANSparkMAXIDs.INTAKE_ROLLER_ID, CANSparkLowLevel.MotorType.kBrushless);
        this.laserCan = new LaserCANWrapper(IDs.LaseCANIDs.INTAKE_LASERCAN_ID);

        laserCan.setRangingMode(RANGE_MODE);
        laserCan.setRegionOfInterest(LASER_CAN_REGION_OF_INTEREST);
        laserCan.setTimingBudget(TIMING_BUDGET);
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
        inputs.isNoteIn = laserCan.getMeasurement().distance_mm < INTAKE_WIDTH;

    }

}
