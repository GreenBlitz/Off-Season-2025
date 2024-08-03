package frc.robot.subsystems.intake.pivot.talonfx;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.intake.pivot.IPivot;
import frc.robot.subsystems.intake.pivot.PivotInputsAutoLogged;
import frc.utils.devicewrappers.TalonFXWrapper;

import static frc.robot.constants.GlobalConstants.DEFAULT_SIGNALS_FREQUENCY_HERTZ;
import static frc.robot.constants.IDs.CANCodersIDs.INTAKE_PIVOT_ENCODER;
import static frc.robot.constants.IDs.TalonFXIDs.PIVOT_MOTOR;
import static frc.robot.subsystems.intake.pivot.talonfx.TalonFXPivotConstants.GEAR_RATIO;

public class TalonFXPivot implements IPivot {

    private TalonFXWrapper motor;
    private CANcoder canCoder;
    private PositionVoltage positionControl;
    private final StatusSignal<Double> motorPositionSignal;
    private final StatusSignal<Double> motorVoltageSignal;
    private final StatusSignal<Double> motorCurrentSignal;
    private final StatusSignal<Double> encoderPositionSignal;
    private final StatusSignal<Double> encoderVoltageSignal;
    public TalonFXPivot (){
        this.motor = new TalonFXWrapper(PIVOT_MOTOR);
        this.canCoder = new CANcoder(INTAKE_PIVOT_ENCODER.ID(),INTAKE_PIVOT_ENCODER.busChain().getChainName());

        FeedbackConfigs feedbackConfigs = new FeedbackConfigs();

        feedbackConfigs.RotorToSensorRatio = GEAR_RATIO;
        feedbackConfigs.FeedbackRemoteSensorID = INTAKE_PIVOT_ENCODER.ID();
        feedbackConfigs.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;

        this.motor.getConfigurator().apply(feedbackConfigs);
        this.positionControl = new PositionVoltage(0);

        motorPositionSignal = motor.getPosition().clone();
        motorVoltageSignal = motor.getMotorVoltage().clone();
        motorCurrentSignal = motor.getStatorCurrent().clone();

        encoderPositionSignal = canCoder.getAbsolutePosition().clone();
        encoderVoltageSignal = canCoder.getSupplyVoltage().clone();

        optimizeCANBusSignals(
                motorCurrentSignal,
                motorCurrentSignal,
                motorVoltageSignal,
                encoderPositionSignal,
                encoderVoltageSignal
        );
    }

    @SafeVarargs
    private void optimizeCANBusSignals (StatusSignal<Double>... signals){
        BaseStatusSignal.setUpdateFrequencyForAll(
                DEFAULT_SIGNALS_FREQUENCY_HERTZ,
                signals
        );
    }
    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    public void setPosition(Rotation2d targetPosition) {
        motor.setControl(positionControl.withPosition(targetPosition.getRotations()));
    }

    @Override
    public void updateInputs(PivotInputsAutoLogged inputs) {
        BaseStatusSignal.refreshAll(
                motorCurrentSignal,
                motorCurrentSignal,
                motorVoltageSignal,
                encoderPositionSignal,
                encoderVoltageSignal
        );
        inputs.motorPosition = Rotation2d.fromRotations(motorPositionSignal.getValue());
        inputs.outputCurrent = motorCurrentSignal.getValue();
        inputs.appliedOutput = motorVoltageSignal.getValue();

        inputs.encoderPosition = Rotation2d.fromRotations(encoderPositionSignal.getValue());
        inputs.isAbsoluteEncoderAlive = encoderVoltageSignal.getValue() != 4;
    }

}
