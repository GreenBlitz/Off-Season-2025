package frc.robot.subsystems.shooter.shooterNeo;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.constants.IDs;
import frc.robot.subsystems.shooter.IShooter;
import frc.robot.subsystems.shooter.ShooterInputsAutoLogged;

public class ShooterNEO implements IShooter {

    private CANSparkMax upperMotor;
    private CANSparkMax lowerMotor;

    private ShooterNEO(){
        upperMotor=new CANSparkMax(IDs.CANSparkMAXIDs.SHOOTER_UPPER_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);
        lowerMotor=new CANSparkMax(IDs.CANSparkMAXIDs.SHOOTER_LOWER_MOTOR_ID, CANSparkLowLevel.MotorType.kBrushless);

        upperMotor.setInverted(NeoConstants.IS_UPPER_MOTOR_INVERTED);
        lowerMotor.setInverted(NeoConstants.IS_LOWER_MOTOR_INVERTED);
    }


    @Override
    public void setPower(double power) {
        upperMotor.set(power);
        lowerMotor.set(power);
    }

    @Override
    public void setVelocity(Rotation2d velocity) {
        upperMotor.getPIDController().setReference(velocity.getRotations(), CANSparkBase.ControlType.kVelocity);
        lowerMotor.getPIDController().setReference(velocity.getRotations(), CANSparkBase.ControlType.kVelocity);

    }

    @Override
    public void setVoltage(double voltage) {
        upperMotor.getPIDController().setReference(voltage, CANSparkBase.ControlType.kVoltage);
        lowerMotor.getPIDController().setReference(voltage, CANSparkBase.ControlType.kVoltage);


    }

    @Override
    public void updateInputs(ShooterInputsAutoLogged inputs) {
        inputs.upperShooterVelocity= Rotation2d.fromRotations(upperMotor.getEncoder().getVelocity());
        inputs.lowerShooterVelocity= Rotation2d.fromRotations(lowerMotor.getEncoder().getVelocity());

        inputs.upperShooterCurrent = upperMotor.getOutputCurrent();
        inputs.lowerShooterCurrent = lowerMotor.getOutputCurrent();

        inputs.lowerShooterOutput= lowerMotor.getAppliedOutput();

    }





}
